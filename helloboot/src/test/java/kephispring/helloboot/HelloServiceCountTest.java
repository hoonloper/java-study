package kephispring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

//@HellobootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloServiceCountTest {
    @Autowired
    HelloService helloService;
    @Autowired
    HelloRepository helloRepository;

    // 애플리케이션 초기화 완료시 테이블 만들어줌
    // @BeforeEach
    // void init() {
    //     jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    // }

    @Test
    void sayHelloIncreaseCount() {
        IntStream.rangeClosed(1, 10).forEach(count -> {
            helloService.sayHello("Kephi");
            Assertions.assertThat(helloRepository.countOf("Kephi")).isEqualTo(count);
        });
    }
}
