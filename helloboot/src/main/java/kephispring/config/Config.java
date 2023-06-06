package kephispring.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

// 컴포넌트 스캔 대상에서 제외해야 한다. 즉 자동 구성 설정해야 하고, 사용자 레벨에서 사용되면 안됨
@Configuration // @Component를 가지고 있다.
public class Config {
    // 이 두개를 autoconfig에 각각 파일로 분리
//    @Bean
//    public ServletWebServerFactory serverFactory() {
//        return new TomcatServletWebServerFactory();
//    }
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet(); // 라이프 사이클 메소드
//    }
}
