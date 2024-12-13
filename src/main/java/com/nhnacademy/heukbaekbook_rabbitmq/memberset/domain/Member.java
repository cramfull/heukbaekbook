package com.nhnacademy.heukbaekbook_rabbitmq.memberset.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "members")
public class Member extends Customer {

    @NotNull
    @Column(name = "member_login_id")
    private String loginId;

    @NotNull
    @Column(name = "member_password")
    private String password;

    @NotNull
    @Column(name = "member_birth")
    private Date birth;

    @NotNull
    @Column(name = "member_created_at")
    private LocalDateTime createdAt;

    @Column(name = "member_last_login_at")
    private LocalDateTime lastLoginAt;

    @NotNull
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "member_status")
    private MemberStatus status;

}
