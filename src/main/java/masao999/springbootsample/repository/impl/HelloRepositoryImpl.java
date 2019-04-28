package masao999.springbootsample.repository.impl;

import masao999.springbootsample.repository.HelloRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryImpl implements HelloRepository {

    public String hello() {
        return "hello";
    }
}
