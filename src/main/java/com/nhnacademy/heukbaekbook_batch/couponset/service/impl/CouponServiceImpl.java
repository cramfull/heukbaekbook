package com.nhnacademy.heukbaekbook_batch.couponset.service.impl;

import com.nhnacademy.heukbaekbook_batch.couponset.client.CouponClient;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.Coupon;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.CouponPolicy;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.MemberCoupon;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.CouponStatus;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.CouponType;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.DiscountType;
import com.nhnacademy.heukbaekbook_batch.couponset.dto.CouponResponse;
import com.nhnacademy.heukbaekbook_batch.couponset.repository.CouponPolicyRepository;
import com.nhnacademy.heukbaekbook_batch.couponset.repository.CouponRepository;
import com.nhnacademy.heukbaekbook_batch.couponset.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponPolicyRepository couponPolicyRepository;
    private final CouponRepository couponRepository;

    @Override
    public MemberCoupon issueBirthdayCoupon(Long customerId, Long couponId) {
        return MemberCoupon.builder()
                .customerId(customerId)
                .couponId(couponId)
                .isCouponUsed(false)
                .couponIssuedAt(LocalDateTime.now())
                .couponExpirationAt(LocalDateTime.now().plusMonths(1L))     // 생일쿠폰은 available 1달
                .build();
    }

    @Override
    public Coupon getBirthdayCoupon(int month) {
        Coupon birthdayCoupon = couponRepository.findByCouponTypeAndCouponStatus(CouponType.BIRTHDAY, CouponStatus.ABLE);
        if (birthdayCoupon == null) {
            CouponPolicy couponPolicy = couponPolicyRepository.findByDiscountTypeAndDiscountAmount(DiscountType.FIXED, BigDecimal.valueOf(1000));
            if(couponPolicy == null) {
                couponPolicy = CouponPolicy.builder().discountType(DiscountType.FIXED).discountAmount(BigDecimal.valueOf(1000)).minimumPurchaseAmount(BigDecimal.valueOf(10000)).build();
            }
            birthdayCoupon =
                    couponRepository.save(Coupon.builder()
                            .couponPolicy(couponPolicy)
                            .couponQuantity(-1)
                            .couponStatus(CouponStatus.ABLE)
                            .availableDuration(30)
                            .couponTimeStart(LocalDateTime.now())
                            .couponTimeEnd(LocalDateTime.now().plusMonths(1L))
                            .couponName(month+"월 생일 쿠폰")
                            .couponDescription(month+"월 생일 쿠폰")
                            .couponType(CouponType.BIRTHDAY)
                    .build());
        }

        return birthdayCoupon;
    }
}
