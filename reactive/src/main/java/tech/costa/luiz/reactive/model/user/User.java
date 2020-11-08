package tech.costa.luiz.reactive.model.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "user")
//@NamedQuery(name = "Users.findAll", query = "SELECT u FROM User u ORDER BY u.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class User extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "usersSequence", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "usersSequence")
    private Integer id;

    @Column(length = 40, unique = false)
    private String name;

    @Column(length = 40, unique = false)
    private String lastName;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
