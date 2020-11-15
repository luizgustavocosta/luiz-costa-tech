package tech.costa.luiz.reactive_db;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
/**
 * Code modified from https://developer.okta.com/blog/2018/09/24/reactive-apis-with-spring-webflux
 */

@Component
@AllArgsConstructor
public class ExampleHandler {

    private final ExampleService service;

    public Mono<ServerResponse> all(ServerRequest serverRequest) {
        return defaultReadResponse(this.service.all());
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return defaultReadResponse(this.service.get(id(serverRequest)));
    }

    public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Flux<Example> flux = serverRequest
                .bodyToFlux(Example.class)
                .flatMap(this.service::create);
        return defaultWriteResponse(flux);
    }

    public Mono<ServerResponse> updateById(ServerRequest serverRequest) {
        return null;
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Example> examples) {
        return Mono
                .from(examples)
                .flatMap(example -> ServerResponse
                        .created(URI.create("/examples/" + example.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<Example> profiles) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(profiles, Example.class);
    }

    private static String id(ServerRequest serverRequest) {
        return serverRequest.pathVariable("id");
    }
}
