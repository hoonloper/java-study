package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.utill.PostFixtureFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Transactional // SQL이 롤백이 된다.
@SpringBootTest
class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void bulkInsert() {
        var easyRandom = PostFixtureFactory.get(
                3L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2023, 4, 4)
        );
        int _1man = 10000;
        var stopWatch = new StopWatch();
        stopWatch.start();
        var posts = IntStream.range(0, _1man * 100)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        stopWatch.stop();
        System.out.println("객체 생성 시간" +stopWatch.getTotalTimeSeconds());

        var queryStopWatch = new StopWatch();

        queryStopWatch.start();
        postRepository.bulkInsert(posts);
        queryStopWatch.stop();
        System.out.println("Insert 시간" +stopWatch.getTotalTimeSeconds());
    }
}
