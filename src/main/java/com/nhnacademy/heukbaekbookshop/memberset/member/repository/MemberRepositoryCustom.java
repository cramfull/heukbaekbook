package com.nhnacademy.heukbaekbookshop.memberset.member.repository;

import com.nhnacademy.heukbaekbookshop.memberset.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> searchByCustomerId(Long customerId);
}
