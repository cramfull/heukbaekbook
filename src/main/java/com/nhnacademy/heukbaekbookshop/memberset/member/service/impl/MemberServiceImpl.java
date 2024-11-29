package com.nhnacademy.heukbaekbookshop.memberset.member.service.impl;

import com.nhnacademy.heukbaekbookshop.book.exception.book.BookNotFoundException;
import com.nhnacademy.heukbaekbookshop.common.util.Converter;
import com.nhnacademy.heukbaekbookshop.common.util.Formatter;
import com.nhnacademy.heukbaekbookshop.memberset.address.domain.MemberAddress;
import com.nhnacademy.heukbaekbookshop.memberset.address.dto.MemberAddressResponse;
import com.nhnacademy.heukbaekbookshop.memberset.customer.domain.Customer;
import com.nhnacademy.heukbaekbookshop.memberset.grade.domain.Grade;
import com.nhnacademy.heukbaekbookshop.memberset.grade.dto.GradeDto;
import com.nhnacademy.heukbaekbookshop.memberset.grade.dto.mapper.GradeMapper;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.mapper.MemberMapper;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.request.MemberCreateRequest;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.request.MemberUpdateRequest;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.request.OAuthMemberCreateRequest;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MemberDetailResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MemberResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MyPageOrderDetailResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MyPageResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.exception.InvalidPasswordException;
import com.nhnacademy.heukbaekbookshop.memberset.member.exception.MemberAlreadyExistException;
import com.nhnacademy.heukbaekbookshop.memberset.member.exception.MemberNotFoundException;
import com.nhnacademy.heukbaekbookshop.memberset.member.domain.Member;
import com.nhnacademy.heukbaekbookshop.memberset.member.domain.MemberStatus;
import com.nhnacademy.heukbaekbookshop.memberset.customer.repository.CustomerRepository;
import com.nhnacademy.heukbaekbookshop.memberset.grade.repository.GradeRepository;
import com.nhnacademy.heukbaekbookshop.memberset.address.repository.MemberAddressRepository;
import com.nhnacademy.heukbaekbookshop.memberset.member.repository.MemberRepository;
import com.nhnacademy.heukbaekbookshop.memberset.member.service.MemberService;
import com.nhnacademy.heukbaekbookshop.order.domain.Order;
import com.nhnacademy.heukbaekbookshop.order.domain.OrderBook;
import com.nhnacademy.heukbaekbookshop.order.dto.response.*;
import com.nhnacademy.heukbaekbookshop.order.exception.OrderNotFoundException;
import com.nhnacademy.heukbaekbookshop.order.repository.OrderRepository;
import com.nhnacademy.heukbaekbookshop.point.history.domain.PointHistory;
import com.nhnacademy.heukbaekbookshop.point.history.event.SignupEvent;
import com.nhnacademy.heukbaekbookshop.point.history.exception.PointNotFoundException;
import com.nhnacademy.heukbaekbookshop.point.history.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final CustomerRepository customerRepository;
    private final GradeRepository gradeRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final OrderRepository orderRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    @Transactional
    public MemberResponse createMember(MemberCreateRequest memberCreateRequest) {
        if (memberRepository.existsByLoginId(memberCreateRequest.loginId())) {
            throw new MemberAlreadyExistException();
        }
        if (memberRepository.existsByEmail(memberCreateRequest.email())) {
            throw new MemberAlreadyExistException();
        }

        Grade grade = gradeRepository.findById(1L).orElseThrow(NoSuchElementException::new);

        Member member = MemberMapper.createMemberEntity(memberCreateRequest, grade, bCryptPasswordEncoder.encode(memberCreateRequest.password()));
        customerRepository.save(member);

        MemberAddress memberAddress = MemberMapper.createMemberAddressEntity(memberCreateRequest, member);
        memberAddressRepository.save(memberAddress);

        eventPublisher.publishEvent(new SignupEvent(member.getId()));
        return MemberMapper.createMemberResponse(member);
    }

    @Override
    @Transactional
    public MemberResponse createOAuthMember(OAuthMemberCreateRequest oAuthMemberCreateRequest) {
        if (memberRepository.existsByLoginId(oAuthMemberCreateRequest.loginId())) {
            throw new MemberAlreadyExistException();
        }
        if (memberRepository.existsByEmail(oAuthMemberCreateRequest.email())) {
            throw new MemberAlreadyExistException();
        }

        Grade grade = gradeRepository.findById(1L).orElseThrow(NoSuchElementException::new);

        Member member = MemberMapper.createOAuthMemberEntity(oAuthMemberCreateRequest, grade, bCryptPasswordEncoder.encode(oAuthMemberCreateRequest.password()));
        customerRepository.save(member);

        eventPublisher.publishEvent(new SignupEvent(member.getId()));

        return MemberMapper.createMemberResponse(member);
    }

    @Override
    public MemberResponse getMember(Long customerId) {
        return MemberMapper.createMemberResponse(memberRepository.findById(customerId)
                .orElseThrow(MemberNotFoundException::new));
    }

    @Override
    @Transactional
    public MemberResponse updateMember(Long customerId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(customerId).orElseThrow(MemberNotFoundException::new);

        if (Objects.nonNull(memberUpdateRequest.oldPassword())
                && !bCryptPasswordEncoder.matches(memberUpdateRequest.oldPassword(), member.getPassword())) {
            throw new InvalidPasswordException("현재 비밀번호가 일치하지 않습니다. 다시 시도해주세요");
        }
        if (Objects.nonNull(memberUpdateRequest.newPassword()) &&
                memberUpdateRequest.oldPassword().equals(memberUpdateRequest.newPassword())) {
            throw new InvalidPasswordException("현재 비밀번호와 새 비밀번호가 같습니다. 다시 시도해주세요");
        }

        return MemberMapper.createMemberResponse(member.modifyMember(memberUpdateRequest,
                Objects.nonNull(memberUpdateRequest.newPassword())
                        ? bCryptPasswordEncoder.encode(memberUpdateRequest.newPassword()) : null));
    }

    @Override
    public boolean existsLoginId(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    @Override
    public boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public MemberDetailResponse getMemberDetail(Long customerId) {
        Member member = memberRepository.findById(customerId)
                .orElseThrow(MemberNotFoundException::new);

        PointHistory pointHistory = pointHistoryRepository.findFirstByMemberIdOrderByCreatedAtDesc(customerId)
                .orElseThrow(() -> new PointNotFoundException(customerId + " point history not found"));

        return new MemberDetailResponse(
                member.getId(),
                member.getName(),
                member.getPhoneNumber(),
                member.getEmail(),
                pointHistory.getAmount(),
                member.getMemberAddresses().stream()
                        .map(memberAddress -> new MemberAddressResponse(
                                memberAddress.getId(),
                                memberAddress.getPostalCode(),
                                memberAddress.getRoadNameAddress(),
                                memberAddress.getDetailAddress(),
                                memberAddress.getAlias()
                        )).collect(Collectors.toList())
        );
    }

    @Override
    public MyPageResponse getMyPageResponse(Long customerId) {
        Member member = memberRepository.searchByCustomerId(customerId)
                .orElseThrow(MemberNotFoundException::new);

        GradeDto gradeDto = GradeMapper.createGradeResponse(member.getGrade());

        List<Order> orders = orderRepository.searchByCustomerId(customerId);

        List<OrderSummaryResponse> orderSummaryResponses = orders.stream()
                .map(this::createOrderSummaryResponse)
                .collect(Collectors.toList());

        return new MyPageResponse(gradeDto, new OrderResponse(orderSummaryResponses));
    }

    private OrderSummaryResponse createOrderSummaryResponse(Order order) {
        // 제목 생성 로직
        String title = createOrderTitle(order);

        int size = order.getOrderBooks().size();
        String totalPrice = Formatter.formatPrice(order.getTotalPrice());

        // OrderSummaryResponse 생성
        return new OrderSummaryResponse(
                order.getCreatedAt().toLocalDate(),
                order.getTossOrderId(),
                title,
                order.getStatus().getKorean(),
                order.getCustomerName(),
                totalPrice + "/" + size,
                new DeliverySummaryResponse(order.getDelivery().getRecipient())
        );
    }

    private String createOrderTitle(Order order) {
        int size = order.getOrderBooks().size() - 1;
        String title = order.getOrderBooks().stream()
                .findFirst()
                .map(orderBook -> orderBook.getBook().getTitle())
                .orElse("제목 없음");
        return size > 0 ? title + " 외 " + size + "종" : title;
    }

    @Override
    public MyPageOrderDetailResponse getMyPageDetailResponse(Long customerId, String tossOrderId) {
        Member member = memberRepository.searchByCustomerId(customerId)
                .orElseThrow(MemberNotFoundException::new);

        Grade grade = member.getGrade();
        GradeDto gradeDto = GradeMapper.createGradeResponse(grade);

        Order order = orderRepository.searchByTossOrderId(tossOrderId)
                .orElseThrow(() -> new OrderNotFoundException(tossOrderId + " order not found"));

        OrderDetailResponse orderDetailResponse = OrderDetailResponse.of(order);


        return new MyPageOrderDetailResponse(gradeDto, orderDetailResponse);
    }

    @Override
    @Transactional
    public void changeMemberStatus(Long customerId, MemberStatus newStatus) {
        Member member = memberRepository.findById(customerId).orElseThrow(MemberNotFoundException::new);
        member.setStatus(newStatus);
    }

    @Override
    public GradeDto getMembersGrade(Long customerId) {
        return GradeMapper.createGradeResponse(
                memberRepository.findGradeByMemberId(customerId)
                        .orElseThrow(MemberNotFoundException::new));
    }

}
