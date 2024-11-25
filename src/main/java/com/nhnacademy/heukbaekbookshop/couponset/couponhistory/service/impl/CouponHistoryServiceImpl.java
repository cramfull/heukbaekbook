package com.nhnacademy.heukbaekbookshop.couponset.couponhistory.service.impl;

import com.nhnacademy.heukbaekbookshop.couponset.couponhistory.domain.CouponHistory;
import com.nhnacademy.heukbaekbookshop.couponset.membercoupon.domain.MemberCoupon;
import com.nhnacademy.heukbaekbookshop.couponset.couponhistory.dto.mapper.CouponHistoryMapper;
import com.nhnacademy.heukbaekbookshop.couponset.couponhistory.dto.request.CouponHistoryRequest;
import com.nhnacademy.heukbaekbookshop.couponset.couponhistory.dto.response.CouponHistoryResponse;
import com.nhnacademy.heukbaekbookshop.couponset.couponhistory.repository.CouponHistoryRepository;
import com.nhnacademy.heukbaekbookshop.couponset.membercoupon.repository.MemberCouponRepository;
import com.nhnacademy.heukbaekbookshop.couponset.couponhistory.service.CouponHistoryService;
import com.nhnacademy.heukbaekbookshop.order.domain.OrderBook;
import com.nhnacademy.heukbaekbookshop.order.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponHistoryServiceImpl implements CouponHistoryService {
    private final CouponHistoryRepository couponHistoryRepository;
    private final OrderBookRepository orderBookRepository;
    private final MemberCouponRepository memberCouponRepository;

    @Override
    @Transactional
    public void createCouponHistory(CouponHistoryRequest couponHistoryRequest) {
        MemberCoupon memberCoupon = memberCouponRepository.findById(couponHistoryRequest.memberCouponId())
                .orElseThrow(() -> new IllegalStateException("해당 ID의 회원 쿠폰을 찾을 수 없습니다 : " +
                        couponHistoryRequest.memberCouponId()));

        OrderBook orderBook = orderBookRepository.findByOrderIdAndBookId(
                couponHistoryRequest.orderId(), couponHistoryRequest.bookId());
        if (orderBook == null) {
            throw new IllegalStateException("해당 주문 ID에 대한 주문 도서를 찾을 수 없습니다 : " +
                    couponHistoryRequest.orderId() + " 해당 도서 ID에 대한 주문 도서를 찾을 수 없습니다 : " + couponHistoryRequest.bookId());
        }

        CouponHistory couponHistory = CouponHistoryMapper.toCouponHistoryEntity(memberCoupon, orderBook);

        couponHistoryRepository.save(couponHistory);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<CouponHistoryResponse> getCouponHistoryByCustomerId(Long memberId, Pageable pageable) {
        return couponHistoryRepository.findByMemberCoupon_Member_Id(pageable, memberId)
                .map(CouponHistoryMapper::toResponse);
    }
}
