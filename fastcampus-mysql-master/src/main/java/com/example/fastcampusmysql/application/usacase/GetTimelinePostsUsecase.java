package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.entity.Timeline;
import com.example.fastcampusmysql.domain.post.service.TimelineReadService;
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

    private final TimelineReadService timelineReadService;

    public PageCursor<Post> excute(Long memberId, CursorRequest cursorRequest) {
        var followings= followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> excuteByTimeline(Long memberId, CursorRequest cursorRequest) {
        /*
            1. 타임라인 테이블을 조회
            2. 1번에 해당하는 게시글을 조회한다.
         */
        var pagedTimelines= timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = pagedTimelines.body().stream().map(Timeline::getPostId).toList();
        var posts = postReadService.getPosts(postIds);

        return new PageCursor(pagedTimelines.nextCursorRequest(), posts);
    }
}
