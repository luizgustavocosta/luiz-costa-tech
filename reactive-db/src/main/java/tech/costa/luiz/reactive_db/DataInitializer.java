package tech.costa.luiz.reactive_db;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//class DataInitializer implements ApplicationRunner {
//
//    private final ExampleRepository repository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        log.info("start data initialization...");
//        this.repository
//                .saveAll(
//                        Arrays.asList(
//                                Example.builder().title("Post one").content("The content of post one").build(),
//                                Example.builder().title("Post tow").content("The content of post tow").build()
//                        )
//                )
//                .thenMany(
//                        this.repository.findAll()
//                )
//                .subscribe((data) -> log.info("post:" + data),
//                        (err) -> log.error("error" + err),
//                        () -> log.info("initialization is done...")
//                );
//    }
//}

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

class DataInitializer extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        //ConnectionFactory factory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        //see: https://github.com/spring-projects/spring-data-r2dbc/issues/269
//        return new H2ConnectionFactory(
//                H2ConnectionConfiguration.builder()
//                        //.inMemory("testdb")
//                        .file("./testdb")
//                        .username("user")
//                        .password("password").build()
//        );
        return H2ConnectionFactory.inMemory("testdb");
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}