package com.nhnacademy.heukbaekbook_rabbitmq.couponset.service.impl;


import com.nhnacademy.heukbaekbook_rabbitmq.couponset.domain.Coupon;
import com.nhnacademy.heukbaekbook_rabbitmq.couponset.domain.MemberCoupon;
import com.nhnacademy.heukbaekbook_rabbitmq.couponset.dto.requset.CouponIssueRequest;
import com.nhnacademy.heukbaekbook_rabbitmq.couponset.repository.MemberCouponRepository;
import com.nhnacademy.heukbaekbook_rabbitmq.couponset.service.CouponIssueService;
import com.nhnacademy.heukbaekbook_rabbitmq.memberset.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponIssueServiceImpl implements CouponIssueService {

    private final EntityManager entityManager;
    private final MemberCouponRepository memberCouponRepository;

    @Override
    @Transactional
    public void issueCouponAsync(CouponIssueRequest couponIssueRequest) {
        MemberCoupon memberCoupon = createMemberCouponProxy(couponIssueRequest);
        memberCouponRepository.save(memberCoupon);
    }


    private MemberCoupon createMemberCouponProxy(CouponIssueRequest couponIssueRequest) {
        Member memberProxy = entityManager.getReference(Member.class, couponIssueRequest.customerId());
        Coupon couponProxy = entityManager.getReference(Coupon.class, couponIssueRequest.couponId());

        return MemberCoupon.builder()
                .member(memberProxy)
                .coupon(couponProxy)
                .expirationDate(couponIssueRequest.couponExpirationDate())
                .build();
    }
}
