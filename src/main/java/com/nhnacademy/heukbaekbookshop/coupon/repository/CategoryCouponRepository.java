package com.nhnacademy.heukbaekbookshop.coupon.repository;

import com.nhnacademy.heukbaekbookshop.coupon.domain.CategoryCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCouponRepository extends JpaRepository<CategoryCoupon, Long> {
    Page<CategoryCoupon> findByCategoryId(Pageable pageable, Long categoryId);
}
