package com.nhnacademy.heukbaekbook_batch.couponset;

import com.nhnacademy.heukbaekbook_batch.couponset.domain.MemberCoupon;
import com.nhnacademy.heukbaekbook_batch.couponset.service.CouponService;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BirthDayCouponBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final CouponService couponService;

    @Bean
    public Job birthdayCouponJob(Step birthdayCouponStep) {
        return new JobBuilder("birthdayCouponJob", jobRepository)
                .start(birthdayCouponStep)
                .build();
    }

    @Bean
    public Step birthdayCouponStep(JpaPagingItemReader<Long> memberItemReader,
                                   ItemProcessor<Long, MemberCoupon> memberCouponItemProcessor,
                                   JpaItemWriter<MemberCoupon> memberCouponItemWriter){
        return new StepBuilder("birthdayCouponStep", jobRepository)
                .<Long, MemberCoupon>chunk(1000,transactionManager)
                .reader(memberItemReader)
                .processor(memberCouponItemProcessor)
                .writer(memberCouponItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Long> memberItemReader(){
        Map<String, Object> monthParam = new HashMap<>();
        monthParam.put("birthMonth", LocalDateTime.now().getMonthValue());
        return new JpaPagingItemReaderBuilder<Long>()
                .name("memberItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(1000)
                .queryString(
                        "SELECT m.id FROM BatchMember m WHERE FUNCTION('MONTH', m.birth) = :birthMonth ORDER BY m.id ASC")
                .parameterValues(monthParam)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Long, MemberCoupon> memberCouponItemProcessor(@Value("#{jobParameters['couponId']}") Long couponId){
        return customerId -> couponService.issueBirthdayCoupon(customerId, couponId);
    }


    @Bean
    public JpaItemWriter<MemberCoupon> memberCouponJpaItemWriter(){
        return new JpaItemWriterBuilder<MemberCoupon>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
    }

}
