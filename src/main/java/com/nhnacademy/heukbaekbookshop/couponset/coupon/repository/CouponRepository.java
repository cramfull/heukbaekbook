package com.nhnacademy.heukbaekbookshop.couponset.coupon.repository;

import com.nhnacademy.heukbaekbookshop.couponset.coupon.domain.BookCoupon;
import com.nhnacademy.heukbaekbookshop.couponset.coupon.domain.Coupon;
import com.nhnacademy.heukbaekbookshop.couponset.coupon.domain.enums.CouponStatus;
import com.nhnacademy.heukbaekbookshop.couponset.coupon.domain.enums.CouponType;
import com.nhnacademy.heukbaekbookshop.couponset.coupon.dto.response.BookCouponResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponRepositoryCustom {
    Coupon findByCouponType(CouponType couponType);
    boolean existsByCouponType(CouponType couponType);

    Coupon findByCouponTypeAndCouponStatus(CouponType couponType, CouponStatus couponStatus);

}
