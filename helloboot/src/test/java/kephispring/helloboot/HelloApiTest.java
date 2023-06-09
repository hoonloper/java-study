package kephispring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 테스트 실행을 위한 애플리케이션 구동
public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=SpringBoot
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "SpringBoot");

        // status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type text/plain)
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello SpringBoot
        Assertions.assertThat(res.getBody()).isEqualTo("*Hello SpringBoot*");
    }

    // 예외 테스트
    @Test
    void falisHelloApi() {
        // http localhost:8080/hello?name=SpringBoot
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
