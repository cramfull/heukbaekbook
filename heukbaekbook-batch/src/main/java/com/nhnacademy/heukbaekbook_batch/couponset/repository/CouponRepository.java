package com.nhnacademy.heukbaekbook_batch.couponset.repository;

import com.nhnacademy.heukbaekbook_batch.couponset.domain.Coupon;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.CouponStatus;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Coupon findByCouponTypeAndCouponStatus(CouponType couponType, CouponStatus couponStatus);
}
