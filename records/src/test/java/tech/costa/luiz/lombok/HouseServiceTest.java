package tech.costa.luiz.lombok;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("House service")
class HouseServiceTest {

    @Test
    void buildAnObjectThroughTheLombok() {
        HouseService service = new HouseService();
        final House house = service.newHouse();
        assertAll(() -> {
            assertNotNull(house);
            assertEquals("5th Avenue", house.getAddress());
            assertTrue(house.getSquareMeters() > 10);
        });
    }
}