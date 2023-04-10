package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsacase {
    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void excute(Long fromMemberId, Long toMemberId) {
        /*
            도메인의 흐름만 제어하는 역할을 하므로, 비즈니스 로직은 최소화
            1. 입력받은 memberId로 회원조회
            2. FollowWriteService.create()
         */
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
