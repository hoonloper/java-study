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

    // 낙관적 락, 데이터 정합성을 위해
    public void likePostByOptimisticLock(Long postId) {
        // 동시성 문제가 발생하기 좋은 사레, 실무에서는 어노테이션 하나만으로 구현이 가능하기에 쉽게 가능하다.
        // MSA 구조로 가면 다른 도메인으로 사용하기 때문에 비관적 락을 사용하기 어렵다.
        var post = postRepository.findById(postId, false).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
