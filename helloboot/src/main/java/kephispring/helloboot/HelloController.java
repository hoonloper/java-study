package kephispring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// @RequestMapping("/hello") 1. 클래스 레벨을 먼저 참고하고
// @MyComponent 이 어노테이션을 메타 어노테이션(어노테이션 위 어노테이션)을 가진 어노테이션에 붙여도 동일한 효과가 발생한다. Component를 붙이면 빈으로 등록해준다는 것
// @Controller Componet 어노테이션이 포함되어 있다.
@RestController // Controller 확장. @Controller, @ResponseBody를 메타 어노테이션으로 가지고 있다.
public class HelloController {
//    private final ApplicationContext applicationContext;
    private final HelloService helloService;

    // 생성자를 통해 주입, 프로퍼티 세터를 통해 주입하든 빈 오브젝트 타입 정보를 가지고 후보를 찾아와서 자동으로 연결을 해달라
    // 이제 오토와이어링을 자동으로 하겠다는 룰을 만들어서 안써도됨, 단 데코레이터를 쓴다면 얘기가 달라짐
    // @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
//    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
//        this.helloService = helloService;
//        this.applicationContext = applicationContext; // applicationContext를 두번째로 주입하는 방법
//
//        System.out.println(applicationContext);
//    }

    // @RequestMapping(value = "/hello", method = RequestMethod.GET) 옛날방식
    @GetMapping("/hello") // 2. 메소드 레벨을 찾아서 등록한다.
    // @ResponseBody view 파일을 찾지 않고 바디값으로 응답하겠다는 어노테이션, 아래 주석 해결법. RestController는 생략 가능
    public String hello(String name) { // String을 리턴하면 view를 가장 먼저 찾으려고 하는데 없어서 404 에러가 발생한다.
        if(name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }

    /* applicationContext를 첫번째로 주입하는 방법
    implements ApplicationContextAware를 사용해야 한다.
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext); // 자동 주입됐을 때 터미널에 띄워줄 것
        this.applicationContext = applicationContext;
    }
     */
}
