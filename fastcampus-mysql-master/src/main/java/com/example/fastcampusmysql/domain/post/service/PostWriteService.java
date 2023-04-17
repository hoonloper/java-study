package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post
                .builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

    // 트랜잭션 잠금을 획득해야 한다.
    @Transactional
    public void likePost(Long postId) {
        // 동시성 문제가 발생하기 좋은 사레
        var post = postRepository.findById(postId, true).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
