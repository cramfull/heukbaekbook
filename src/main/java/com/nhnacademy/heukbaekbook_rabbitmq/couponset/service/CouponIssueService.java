package com.nhnacademy.heukbaekbook_rabbitmq.couponset.service;


import com.nhnacademy.heukbaekbook_rabbitmq.couponset.dto.requset.CouponIssueRequest;

public interface CouponIssueService {
    void issueCouponAsync(CouponIssueRequest couponIssueRequest);
}
