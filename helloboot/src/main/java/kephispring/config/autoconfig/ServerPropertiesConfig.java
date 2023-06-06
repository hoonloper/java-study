package kephispring.config.autoconfig;

import kephispring.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@MyAutoConfiguration 등록 해제
public class ServerPropertiesConfig {
    // ServerProperties라는 클래스를 제공함
//    @Bean
    public ServerProperties serverProperties(Environment env) {
        // 편하게 자동으로 등록해주는 유틸리티, 사용하는 프로퍼티가 많아지면 클래스가 계속 파생됨
        return Binder.get(env).bind("", ServerProperties.class).get();

        // ServerProperties properties = new ServerProperties();

        // properties.setContextPath(env.getProperty("contextPath"));
        // properties.setPort(Integer.parseInt(env.getProperty("port")));

        // return properties;
    }
}
