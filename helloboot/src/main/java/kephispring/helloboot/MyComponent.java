package kephispring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션을 만들 때 필수 어노테이션 두가지
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component // 직접 만든 어노테이션으로 만들어도 적용이 됨
public @interface MyComponent {
}
