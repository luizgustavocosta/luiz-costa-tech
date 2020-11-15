package tech.costa.luiz.reactive_db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
class ExampleEndpointConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(ExampleHandler handler) {
        return route(i(GET("/examples")), handler::all)
                .andRoute(i(GET("/examples/{id}")), handler::getById)
                .andRoute(i(DELETE("/examples/{id}")), handler::deleteById)
                .andRoute(i(POST("/examples")), handler::create)
                .andRoute(i(PUT("/examples/{id}")), handler::updateById);
    }


    private static RequestPredicate i(RequestPredicate target) {
        return new CaseInsensitiveRequestPredicate(target);
    }
}
