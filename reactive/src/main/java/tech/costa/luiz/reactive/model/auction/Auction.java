package tech.costa.luiz.reactive.model.auction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Table(name = "auction")
//@NamedQuery(name = "Auctions.findAll", query = "SELECT auction FROM Auction auction ORDER BY auction.id", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
//@Cacheable

public class Auction {

    @Id @GeneratedValue
    private Long id;

}
