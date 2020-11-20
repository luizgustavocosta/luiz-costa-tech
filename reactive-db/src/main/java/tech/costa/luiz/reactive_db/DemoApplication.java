package tech.costa.luiz.reactive_db;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ExampleRepository repository, DatabaseClient client) {
		return args -> {
			client.execute("create table IF NOT EXISTS Examples" +
					"(id SERIAL PRIMARY KEY, title VARCHAR(255), content VARCHAR(255));")
					.fetch().first().subscribe();
			client.execute("DELETE FROM Examples;").fetch().first().subscribe();

			Stream<Example> stream = Stream.of(new Example(null, "Hi this is my first todo!", "Any Content"),
					new Example(null, "This one I have acomplished!", "Any Content"),
					new Example(null, "And this is secret", "Any Content"));

			// initialize the database

			repository.saveAll(Flux.fromStream(stream))
					.then()
					.subscribe(); // execute
		};
	}



}

