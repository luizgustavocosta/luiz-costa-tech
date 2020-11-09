package tech.costa.luiz.reactive.actions.users;

import io.quarkus.vertx.web.Body;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerResponse;
import tech.costa.luiz.reactive.model.user.User;
import tech.costa.luiz.reactive.model.user.Users;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Path("/users")
public class UserController {


    @Inject
    Users users;

    @POST
    @Transactional
    public Uni<User> create(@Body User user, HttpServerResponse response) {
        if (isNull(user) || nonNull(user.getId())) {
            return Uni.createFrom().failure(new IllegalArgumentException("User id invalidly set on request."));
        }
        users.persist(user);
        return Uni.createFrom().item(user);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> findOne(@PathParam("id") String id) {
        return Uni.createFrom().item(users.findById(Long.parseLong(id)));
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<User> findAll() {
        return Multi.createFrom().items(users.streamAll());
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Uni<User> update(@PathParam("id") String id, User user) {
        if (isNull(user) || nonNull(user.getId())) {
            return Uni.createFrom().failure(new IllegalArgumentException("User name was not set on request."));
        }
        users.update("name='" + user.getName() + "', lastName='" + user.getLastName() + "' where id =?1", Long.valueOf(id));
        return Uni.createFrom().item(user);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@org.jboss.resteasy.annotations.jaxrs.PathParam Long id) {
        if (!users.deleteById(id)) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        return Response.status(204).build();
    }
}
