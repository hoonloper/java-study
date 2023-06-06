package kephispring.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false) // MyAutoConfiguration 어노테이션이 붙은 것들은 proxyBeanMethods를 끈 설정으로 불러온다.
public @interface MyAutoConfiguration {
}
