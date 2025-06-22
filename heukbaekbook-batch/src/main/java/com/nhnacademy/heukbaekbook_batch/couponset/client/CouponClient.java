package com.nhnacademy.heukbaekbook_batch.couponset.client;

import com.nhnacademy.heukbaekbook_batch.couponset.dto.CouponPolicyResponse;
import com.nhnacademy.heukbaekbook_batch.couponset.dto.CouponResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "couponClient", url = "http://localhost:8082/api/admin/coupons")
public interface CouponClient {

    @GetMapping("/coupon-type/{couponType}")
    ResponseEntity<CouponResponse> getBirthdayCoupon(@PathVariable String type);
}
