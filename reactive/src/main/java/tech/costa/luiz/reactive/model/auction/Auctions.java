package tech.costa.luiz.reactive.model.auction;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Auctions implements PanacheRepository<Auctions> {
}
