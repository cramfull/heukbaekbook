package com.nhnacademy.heukbaekbook_rabbitmq.couponset.repository;

import com.nhnacademy.heukbaekbook_rabbitmq.couponset.domain.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

}

