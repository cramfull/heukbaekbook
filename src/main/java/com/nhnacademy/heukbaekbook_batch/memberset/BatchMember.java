package com.nhnacademy.heukbaekbook_batch.memberset;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "members")
public class BatchMember extends BatchCustomer {

    @NotNull
    @Column(name = "member_birth")
    private Date birth;

    @Column(name = "member_last_login_at")
    private LocalDateTime lastLoginAt;

    @NotNull
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "member_status")
    private MemberStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private Grade grade;

}
