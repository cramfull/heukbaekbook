package com.nhnacademy.heukbaekbookshop.memberset.member.service;

import com.nhnacademy.heukbaekbookshop.memberset.member.domain.MemberStatus;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.request.MemberCreateRequest;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.request.MemberUpdateRequest;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.request.OAuthMemberCreateRequest;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MemberDetailResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MemberResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MyPageOrderDetailResponse;
import com.nhnacademy.heukbaekbookshop.memberset.member.dto.response.MyPageResponse;

public interface  MemberService {

    MemberResponse createMember(MemberCreateRequest memberCreateRequest);

    MemberResponse createOAuthMember(OAuthMemberCreateRequest oAuthMemberCreateRequest);

    MemberResponse getMember(Long customerId);

    void changeMemberStatus(Long customerId, MemberStatus memberStatus);

    MemberResponse updateMember(Long customerId, MemberUpdateRequest memberUpdateRequest);

    boolean existsLoginId(String loginId);

    boolean existsEmail(String email);

    MemberDetailResponse getMemberDetail(Long customerId);

    MyPageResponse getMyPageResponse(Long customerId);

    MyPageOrderDetailResponse getMyPageDetailResponse(Long customerId, String tossOrderId);
}
