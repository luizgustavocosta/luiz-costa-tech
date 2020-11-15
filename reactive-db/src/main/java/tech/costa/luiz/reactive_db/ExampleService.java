package tech.costa.luiz.reactive_db;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ApplicationEventPublisher publisher;


    @Autowired
    private final ExampleRepository repository;

    public Publisher<Example> all() {
        return Flux.from(repository.findAll());
    }

    public Mono<Example> create(Example example) {
        return repository.save(example)
                .doOnSuccess(event -> this.publisher.publishEvent(new ExampleCreatedEvent(event)));
    }

    public Mono<Example> get(String id) {
        return this.repository.findById(Integer.valueOf(id));
    }

    public Mono<Example> update(Example example) {
        return this.repository
                .findById(example.getId())
                .map(current -> new Example(current.getId(), current.getTitle(), current.getContent()))
                .flatMap(this.repository::save);
    }
}
