package kephispring.config.autoconfig;

import kephispring.config.ConditionalMyOnClass;
import kephispring.config.EnableMyConfigurationProperties;
import kephispring.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;

// @Configuration
@MyAutoConfiguration
@EnableMyConfigurationProperties(ServerProperties.class)
//@Conditional(TomcatWebServerConfig.TomcatCondition.class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
//    @Value("${contextPath:}")
//    String contextPath;
//
//    @Value("${port:8080}") // default값 넣기 ':'
//    int port;

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean // 커스텀을 위한 어노테이션
    public ServletWebServerFactory serverFactory(ServerProperties properties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(properties.getContextPath());
        factory.setPort(properties.getPort());
        // factory.setContextPath(env.getProperty("contextPath")); // 모든 API path에 적용됨, 파라미터에 Environment env
        return factory;
    }


//    static class TomcatCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
//        }
//    }
}
