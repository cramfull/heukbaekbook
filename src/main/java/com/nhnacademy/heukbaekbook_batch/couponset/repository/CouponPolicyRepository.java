package com.nhnacademy.heukbaekbook_batch.couponset.repository;

import com.nhnacademy.heukbaekbook_batch.couponset.domain.CouponPolicy;
import com.nhnacademy.heukbaekbook_batch.couponset.domain.enums.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Long> {

    CouponPolicy findByDiscountTypeAndDiscountAmount(DiscountType discountType, BigDecimal discountAmount);
}
