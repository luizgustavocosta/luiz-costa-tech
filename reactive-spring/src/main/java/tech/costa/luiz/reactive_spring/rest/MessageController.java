package tech.costa.luiz.reactive_spring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/message")
//FIXME This is the first approach
public class MessageController {

    @GetMapping(value = "/generate/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomMessage> generateCustomMessages(@PathVariable String name) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(number -> new CustomMessage(number.toString(), LocalDateTime.now(), name +" "+ number));
    }

    private static class CustomMessage {
        private String id;
        private LocalDateTime date;
        private String message;

        public CustomMessage(String id, LocalDateTime date, String message) {
            this.id = id;
            this.date = date;
            this.message = message;
        }

        public String getId() {
            return id;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public String getMessage() {
            return message;
        }
    }
}
