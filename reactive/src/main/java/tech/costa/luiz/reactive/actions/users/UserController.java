package tech.costa.luiz.reactive.actions.users;

import io.quarkus.vertx.web.Body;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerResponse;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
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
    @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
    @Timed(name = "checksTimer", description = "A measure how long it takes to perform the primality test.", unit = MetricUnits.MILLISECONDS)
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<User> findAll() {
        return Multi.createFrom().items(users.streamAll());
    }


    @Gauge(name = "Random metric", unit = MetricUnits.NONE, description = "Just trying")
    public Long randomMetric() {
        return 42L;
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
