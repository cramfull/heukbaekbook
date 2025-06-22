package com.nhnacademy.heukbaekbook_rabbitmq.couponset.domain;

import com.nhnacademy.heukbaekbook_rabbitmq.memberset.domain.Member;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(name = "is_coupon_used")
    private boolean isCouponUsed;

    @Column(name = "coupon_issued_at")
    private LocalDateTime couponIssuedAt;

    @Column(name = "coupon_expiration_date")
    private LocalDateTime couponExpirationDate;


    @Builder
    private MemberCoupon(Member member, Coupon coupon, LocalDateTime expirationDate) {
        this.member = member;
        this.coupon = coupon;
        this.isCouponUsed = false;
        this.couponIssuedAt = LocalDateTime.now();
        this.couponExpirationDate = expirationDate;
    }


}
