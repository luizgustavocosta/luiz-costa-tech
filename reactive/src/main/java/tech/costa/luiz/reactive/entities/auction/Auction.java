package tech.costa.luiz.reactive.entities.auction;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "auction")
@Cacheable(value = false)

public class Auction extends PanacheEntityBase {

    @Id @GeneratedValue
    private Long id;

}