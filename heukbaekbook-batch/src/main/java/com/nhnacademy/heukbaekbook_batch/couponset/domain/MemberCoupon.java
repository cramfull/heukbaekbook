package com.nhnacademy.heukbaekbook_batch.couponset.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members_coupons")
public class MemberCoupon {

    @Id
    @Column(name = "member_coupon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "coupon_id")
    private Long couponId;

    @NotNull
    @Column(name = "is_coupon_used")
    private boolean isCouponUsed;

    @NotNull
    @Column(name = "coupon_issued_at")
    private LocalDateTime couponIssuedAt;

    @NotNull
    @Column(name = "coupon_expiration_date")
    private LocalDateTime couponExpirationAt;


    @Builder
    public MemberCoupon(Long customerId, Long couponId, boolean isCouponUsed, LocalDateTime couponIssuedAt, LocalDateTime couponExpirationAt) {
        this.customerId = customerId;
        this.couponId = couponId;
        this.isCouponUsed = isCouponUsed;
        this.couponIssuedAt = couponIssuedAt;
        this.couponExpirationAt = couponExpirationAt;
    }
}