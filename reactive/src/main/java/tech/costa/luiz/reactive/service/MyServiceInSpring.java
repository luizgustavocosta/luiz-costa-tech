package tech.costa.luiz.reactive.service;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MyServiceInSpring {

    public MyServiceInSpring(Repository repository) {
        this.repository = repository;
    }

    private final Repository repository;

}

class Repository {

}
