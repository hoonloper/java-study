package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.utils.CursorRequest;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.utils.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {
    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    public PageCursor<Post> excute(Long memberId, CursorRequest cursorRequest) {
        var followings= followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
