package com.nhnacademy.heukbaekbook_batch.couponset.dto;

import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.CouponStatus;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.CouponType;

import java.time.LocalDateTime;

public record CouponResponse(
        Long couponId,
        CouponPolicyResponse couponPolicyResponse,
        CouponStatus couponStatus,
        Integer couponQuantity,
        int availableDuration,
        LocalDateTime couponTimeStart,
        LocalDateTime couponTimeEnd,
        String couponName,
        String couponDescription,
        LocalDateTime couponCreatedAt,
        CouponType couponType
) {
}