package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usacase.CreateFollowMemberUsecase;
import com.example.fastcampusmysql.application.usacase.GetFollowingMemberUsecase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {
    private final CreateFollowMemberUsecase createFollowMemberUsacase;
    private final GetFollowingMemberUsecase getFollowingMemberUsacase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsacase.excute(fromId, toId);
    }
    @GetMapping("/members/{fromId}")
    public List<MemberDto> create(@PathVariable Long fromId) {
        return getFollowingMemberUsacase.excute(fromId);
    }
}
