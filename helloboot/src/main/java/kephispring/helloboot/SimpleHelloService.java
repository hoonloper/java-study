package kephispring.helloboot;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component
@Service // Compoent 어노테이션을 포함하고 있다.
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
