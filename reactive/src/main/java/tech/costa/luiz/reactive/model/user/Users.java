package tech.costa.luiz.reactive.model.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * The type Users.
 */
@ApplicationScoped
public class Users implements PanacheRepository<User> {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    public List<User> findByName(String name) {
        return list("name", name);
    }
}
