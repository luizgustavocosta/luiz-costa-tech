package tech.costa.luiz.reactive.domain;

public class Product {

    private final String id;
    private final String name;
    private final String price;
    private final String status;
    private final Owner owner;
    private final Buyer buyer;

    public Product(String id, String name, String price, String status, Owner owner, Buyer buyer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.owner = owner;
        this.buyer = buyer;
    }
}
