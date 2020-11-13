package tech.costa.luiz.model.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;

    @NotBlank
    @Column(length = 40)
    public String name;

    @NotBlank
    @Column(length = 40)
    public String lastName;

    @Override
    public String toString() {
        return "User{" +

                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
