package tech.costa.luiz.reactive.actions.users;

import tech.costa.luiz.reactive.model.user.User;
import tech.costa.luiz.reactive.model.user.Users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Path("/users")
public class AddUsers {

    private AtomicLong count = new AtomicLong();

    @Inject
    Users users;

    @GET
    @Path("add")
    @Transactional
    public void add() {
        User newUser = new User();
        newUser.setLastName("Bond");
        newUser.setName("James "+count.incrementAndGet());
        users.persist(newUser);

    }
}
