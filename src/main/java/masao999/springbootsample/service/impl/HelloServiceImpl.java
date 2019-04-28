package masao999.springbootsample.service.impl;

import masao999.springbootsample.repository.HelloRepository;
import masao999.springbootsample.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    private final HelloRepository helloRepository;

    public HelloServiceImpl(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public String hello() {
        return helloRepository.hello();
    }
}
