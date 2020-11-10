package tech.costa.luiz.reactive.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class ItemTest implements WithAssertions {

    @Test
    void should_create_an_item() {
        Item item = Item.ItemBuilder.anItem().withId("42").withName("The universe answer").build();
        assertThat(item).as("A new Item instance").isNotNull();
    }

}