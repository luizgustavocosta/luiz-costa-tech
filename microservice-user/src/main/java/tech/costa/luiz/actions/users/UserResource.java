package tech.costa.luiz.actions.users;

import io.quarkus.panache.common.Sort;
import io.quarkus.vertx.web.Body;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.http.HttpServerResponse;
import tech.costa.luiz.model.user.User;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Path("/users")
public class UserResource {

    @POST
    @Transactional
    public Uni<User> create(@Body tech.costa.luiz.model.user.User user, HttpServerResponse response) {
        if (isNull(user)) {
            return Uni.createFrom().failure(new IllegalArgumentException("User id invalidly set on request."));
        }
        User.persist(user);
        return Uni.createFrom().item(user);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<tech.costa.luiz.model.user.User> findOne(@PathParam("id") String id) {
        return Uni.createFrom().item((User) User.findById(Long.parseLong(id)));
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        return User.findAll(Sort.by("name", "lastName")).list();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Uni<User> update(@PathParam("id") String id, User user) {
        if (isNull(user) || nonNull(user.id)) {
            return Uni.createFrom().failure(new IllegalArgumentException("User name was not set on request."));
        }
        User.update("name='" + user.name + "', lastName='" + user.lastName + "' where id =?1", Long.valueOf(id));
        return Uni.createFrom().item(user);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@org.jboss.resteasy.annotations.jaxrs.PathParam Long id) {
        if (!User.deleteById(id)) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        return Response.status(204).build();
    }
}
