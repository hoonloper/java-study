package kephispring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {
    @Test
    void conditional() {
        // true
        // 라이브러리에서 지원하는 빈 테스트
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(MyBean.class);
            Assertions.assertThat(context).hasSingleBean(Config1.class);
        });
        // 구식 테스트
        // AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        // ac.register(Config1.class);
        // ac.refresh();
        // MyBean bean = ac.getBean(MyBean.class);


        // false
        // 라이브러리에서 지원하는 빈 테스트
        new ApplicationContextRunner().withUserConfiguration(Config2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
            Assertions.assertThat(context).doesNotHaveBean(Config2.class);
        });
        // 구식 테스트
        // AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
        // ac2.register(Config2.class);
        // ac2.refresh();
        // MyBean bean2 = ac2.getBean(MyBean.class);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean () {
            return new MyBean();
        }
    }
    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean () {
            return new MyBean();
        }
    }

    static class MyBean {};

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            assert annotationAttributes != null;
            return (Boolean) annotationAttributes.get("value");
        }
    }
}
