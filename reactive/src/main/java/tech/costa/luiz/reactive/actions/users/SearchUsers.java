package tech.costa.luiz.reactive.actions.users;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import reactor.core.publisher.Flux;
import tech.costa.luiz.reactive.model.user.User;
import tech.costa.luiz.reactive.model.user.UserService;
import tech.costa.luiz.reactive.model.user.Users;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.LocalDateTime;

@Path("/users")
public class SearchUsers {

    @Inject
    Users users;

    @Inject
    UserService service;

    @GET
    @Path("generateusers")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Deprecated
    public Multi<CustomMessage> generateUsers() {
        return Multi.createFrom().publisher(
                Flux.interval(Duration.ofSeconds(1))
                        .map(number -> new CustomMessage(number.toString(), LocalDateTime.now(), "ABC " + number)));
    }
    
    @GET
    @Path("{name}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<User> findBy(@PathParam("name") String name) {
        return Multi.createFrom().iterable(users.findByName(name));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> findOne(@PathParam("id") String id) {
        final User user = users.findById(Long.parseLong(id));
        System.out.println("user = " + user);
        return Uni.createFrom().item(users.findById(Long.parseLong(id)));
    }

    @GET
    @Path("")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<User> findAll() {
        return Multi.createFrom().items(users.streamAll());
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
