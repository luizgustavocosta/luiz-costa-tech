package tech.costa.luiz.reactive_db;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

interface ExampleRepository extends ReactiveCrudRepository<Example, Integer> {

    @Query("SELECT * FROM examples WHERE title like $1")
    Flux<Example> findByTitleContains(String name);

}