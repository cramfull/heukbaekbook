package com.nhnacademy.heukbaekbook_batch.couponset;

import com.nhnacademy.heukbaekbook_batch.couponset.domain.Coupon;
import com.nhnacademy.heukbaekbook_batch.couponset.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BirthdayCouponJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job birthdayCouponJob;
    private final CouponService couponService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void runBirthdayCouponJob() {
        try {
            int currentMonth = LocalDate.now().getMonthValue();

            Coupon birthdayCoupon = couponService.getBirthdayCoupon(currentMonth);

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("couponId", birthdayCoupon.getId())
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(birthdayCouponJob, jobParameters);

        } catch (Exception e) {
            log.info("Exception occurred while running job: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}