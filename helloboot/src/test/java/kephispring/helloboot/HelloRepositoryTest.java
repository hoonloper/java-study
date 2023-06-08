package kephispring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

//@HellobootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("Kephi")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("Kephi")).isZero();

        helloRepository.increaseCount("Kephi");
        Assertions.assertThat(helloRepository.countOf("Kephi")).isOne();

        helloRepository.increaseCount("Kephi");
        Assertions.assertThat(helloRepository.countOf("Kephi")).isEqualTo(2);
    }
}
