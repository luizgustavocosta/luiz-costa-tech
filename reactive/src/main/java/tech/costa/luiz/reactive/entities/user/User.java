package tech.costa.luiz.reactive.entities.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Cacheable
public class User extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "usersSequence", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 40, unique = false)
    public String name;

    @Column(length = 40, unique = false)
    public String lastName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
