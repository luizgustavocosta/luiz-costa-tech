package tech.costa.luiz.reactive_spring.domain;

import java.time.ZonedDateTime;

public class Bid {

    private final String id;
    private final ZonedDateTime zonedDateTime;
    private final String price;
    private final Buyer buyer;

    public Bid(String id, ZonedDateTime zonedDateTime, String price, Buyer buyer) {
        this.id = id;
        this.zonedDateTime = zonedDateTime;
        this.price = price;
        this.buyer = buyer;
    }
}
