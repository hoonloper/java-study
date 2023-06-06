package kephispring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* @Configuration 어노테이션
* 스프링은 기본적으로 @Configuration 어노테이션이 붙으면
* 두 가지 빈이 한 가지 빈에 의존할 때 문제 발생을 해결하기 위해
* Proxy를 만들어서 확장한다.
* 이 것은 우리가 바라보는 코드의 예상 동작과 다르게 동작하기 때문에 확실히 인지하고 있어야 한다.
* 스프링 5.2에서 추가된 proxyBeanMethod 옵션을 통해 이 기능을 끌 수 있도록 추가해뒀다.
* */
public class ConfigurationTest {
    @Test
    void configuration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        // 스프링 안에서 빈으로 등록돼서 마법같이 동작한다.
        Assertions.assertThat(bean1.common).isSameAs(bean2.common); // 이건 같다

        // MyConfig myConfig = new MyConfig();
        // Bean1 bean1 = myConfig.bean1();
        // Bean2 bean2 = myConfig.bean2();

        // Assertions.assertThat(bean1.common).isSameAs(bean2.common); // 다르다
    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common); // 일치
    }

    // Proxy 패턴 예시
    static class MyConfigProxy extends MyConfig {
        private Common common;
        @Override
        Common common() {
            if(this.common == null) this.common = super.common();

            return this.common;
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }
    // Bean1 <-- Common
    // Bean2 <-- Common
    // 보통 싱글턴
}
