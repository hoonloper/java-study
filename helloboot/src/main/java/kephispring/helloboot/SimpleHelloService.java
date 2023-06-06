package kephispring.helloboot;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component
@Service // Compoent 어노테이션을 포함하고 있다.
public class SimpleHelloService implements HelloService {
    private final HelloRepository helloRepository;

    public SimpleHelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public String sayHello(String name) {
        this.helloRepository.increaseCount(name);

        return "Hello " + name;
    }

    @Override
    public int countOf(String name) {
        return this.helloRepository.countOf(name);
    }
}
