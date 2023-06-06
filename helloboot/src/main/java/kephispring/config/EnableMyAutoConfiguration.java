package kephispring.config;

import kephispring.config.autoconfig.DispatcherServletConfig;
import kephispring.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // Type class, interface, enum 등등
// @Import({DispatcherServletConfig.class, TomcatWebServerConfig.class}) 컴포넌트 어노테이션이 붙은 클래스들을 import를 통해 구성정보에 추가할 수 있다.
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration {
}
