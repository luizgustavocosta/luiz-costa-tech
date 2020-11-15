package tech.costa.luiz.reactive_db;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.net.URI;
import java.time.Duration;

@RestController()
@RequestMapping(value = "/examples")
@RequiredArgsConstructor
class ExampleController {

    private final ExampleService service;

    private final ExampleRepository repository;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Example> all() {
        return Flux.from(service.all());
    }

    @PostMapping("")
    public Publisher<ResponseEntity<Example>> create(@RequestBody Example example) {
        return this.service.create(example)
                .map(newPost -> ResponseEntity.created(URI.create("/posts/"+newPost.getId()))
                        .contentType(MediaType.APPLICATION_JSON).build());
    }

    @GetMapping("/{id}")
    public Mono<Example> get(@PathVariable("id") Integer id) {
        return this.repository.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Example> update(@PathVariable("id") Integer id, @RequestBody Example example) {
        return this.repository.findById(id)
                .map(currentPost -> {
                    currentPost.setTitle(example.getTitle());
                    currentPost.setContent(example.getContent());

                    return currentPost;
                })
                .flatMap(this.repository::save);
    }

    @GetMapping(value = "/see", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Example> sse() {
        return Flux
                .zip(Flux.interval(Duration.ofSeconds(1)), this.repository.findAll().repeatWhen(Flux::ignoreElements))
                .map(Tuple2::getT2);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id) {
        return this.repository.deleteById(id);
    }

}