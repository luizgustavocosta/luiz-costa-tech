package tech.costa.luiz.reactive.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Auction {

        private Auction(ZonedDateTime closingDate, ZonedDateTime startDate, String id, BigDecimal startAmount,
                      String status, Item item, User seller, User buyer) {
        }
}
