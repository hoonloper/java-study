package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    // @Transactional
    // 대용량 데이터를 처리할 때 여기에 트랜잭션을 걸어야 할까?
    public Long excute(PostCommand postCommand) {
        var postId= postWriteService.create(postCommand);
        var followerMemberIds = followReadService.getFollowers(postCommand.memberId())
                .stream()
                .map(Follow::getFromMemberId)
                .toList();
        timelineWriteService.deliveryToTimeline(postId, followerMemberIds);

        return postId;
    }
}
