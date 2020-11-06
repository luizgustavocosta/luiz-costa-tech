package tech.costa.luiz.reactive.api;

import io.smallrye.mutiny.Multi;
import reactor.core.publisher.Flux;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.LocalDateTime;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<CustomMessage> generateUsers() {
        return Multi.createFrom().publisher(
                Flux.interval(Duration.ofSeconds(1))
                        .map(number -> new CustomMessage(number.toString(), LocalDateTime.now(), "ABC " + number)));
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

        @Override
        public String toString() {
            return "CustomMessage{" +
                    "id='" + id + '\'' +
                    ", date=" + date +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
