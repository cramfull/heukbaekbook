package com.nhnacademy.heukbaekbook_rabbitmq.rabbitmq.listener;

import com.nhnacademy.heukbaekbook_rabbitmq.couponset.dto.requset.CouponIssueRequest;
import com.nhnacademy.heukbaekbook_rabbitmq.couponset.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponIssueListener {

    private final CouponIssueService couponIssueService;

    @Transactional
    @RabbitListener(queues = "heukbaekbook.coupon.queue", concurrency = "1")
    public void receiveMessage(CouponIssueRequest issueMessage) {
        if(isValidMessage(issueMessage)) {
            throw new AmqpException("Invalid message received");
        }

        couponIssueService.issueCouponAsync(issueMessage);
    }


    boolean isValidMessage(CouponIssueRequest issueMessage) {
        return Objects.isNull(issueMessage.couponId())
                || Objects.isNull(issueMessage.couponId())
                || Objects.isNull(issueMessage.couponExpirationDate());
    }
}
