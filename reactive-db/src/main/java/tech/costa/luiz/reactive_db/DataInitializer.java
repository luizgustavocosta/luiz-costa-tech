package tech.costa.luiz.reactive_db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
class DataInitializer implements ApplicationRunner {

    private final ExampleRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("start data initialization...");
        this.repository
                .saveAll(
                        Arrays.asList(
                                Example.builder().title("Post one").content("The content of post one").build(),
                                Example.builder().title("Post tow").content("The content of post tow").build()
                        )
                )
                .thenMany(
                        this.repository.findAll()
                )
                .subscribe((data) -> log.info("post:" + data),
                        (err) -> log.error("error" + err),
                        () -> log.info("initialization is done...")
                );
    }
}