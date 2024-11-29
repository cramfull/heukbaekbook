package com.nhnacademy.heukbaekbook_batch.couponset.service;

import com.nhnacademy.heukbaekbook_batch.couponset.domain.Coupon;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.MemberCoupon;

import java.math.BigDecimal;

public interface CouponService {
    MemberCoupon issueBirthdayCoupon(Long customerId, Long couponId);
    Coupon getBirthdayCoupon(int month);

}
