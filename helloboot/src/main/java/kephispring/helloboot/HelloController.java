package kephispring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping("/hello") // 1. 클래스 레벨을 먼저 참고하고
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // @RequestMapping(value = "/hello", method = RequestMethod.GET) 옛날방식
    @GetMapping // 2. 메소드 레벨을 찾아서 등록한다.
    @ResponseBody // view 파일을 찾지 않고 바디값으로 응답하겠다는 어노테이션, 아래 주석 해결법. RestController는 생략 가능
    public String hello(String name) { // String을 리턴하면 view를 가장 먼저 찾으려고 하는데 없어서 404 에러가 발생한다.
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
