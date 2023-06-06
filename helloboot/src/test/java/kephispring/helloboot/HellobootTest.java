package kephispring.helloboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class) // junit 테스트 확장
@ContextConfiguration(classes = HellobootApplication.class) // 모든 빈 정보를 끌어옴
@TestPropertySource("classpath:/application.properties")
@Transactional // 롤백을 위함
public @interface HellobootTest {
}
