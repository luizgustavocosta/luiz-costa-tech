package tech.costa.luiz.reactive.domain;

import java.math.BigDecimal;

public class Item {

    private final String id;
    private final String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static final class ItemBuilder {
        private String id;
        private String name;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;

        private ItemBuilder() {
        }

        public static ItemBuilder anItem() {
            return new ItemBuilder();
        }

        public ItemBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public ItemBuilder withMaxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public Item build() {
            Item item = new Item(id, name);
            item.maxPrice = this.maxPrice;
            item.minPrice = this.minPrice;
            return item;
        }
    }
}
