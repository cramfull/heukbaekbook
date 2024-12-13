package com.nhnacademy.heukbaekbook_rabbitmq.couponset.dto.requset;

import java.time.LocalDateTime;

public record CouponIssueRequest(
        Long couponId,
        Long customerId,
        LocalDateTime couponExpirationDate
){
}
