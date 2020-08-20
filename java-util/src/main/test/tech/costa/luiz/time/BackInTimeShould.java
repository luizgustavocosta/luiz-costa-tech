package tech.costa.luiz.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.LocalDateTime.ofInstant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Back to the future, playing with the time")
class BackInTimeShould {

    private final ZoneId zoneIdSaoPaulo = ZoneId.of("America/Sao_Paulo");
    private final ZoneId zoneIdBarcelona = ZoneId.of("Europe/Madrid");

    @Test
    @DisplayName("List all timezone ids available")
    void listAllTimezonesIds() {
        List<String> timeZoneId = Stream.of(TimeZone.getAvailableIDs())
                .map(TimeZone::getTimeZone)
                .map(TimeZone::getID)
                .collect(Collectors.toList());

        short expectedDifferentTimezones = 629;
        assertEquals(expectedDifferentTimezones, timeZoneId.size());
    }

    @Test
    @DisplayName("Calculate the difference in hours between two cities, Sao Paulo (Brazil) and Barcelona (Spain)")
    void calculateTheTimeDifferenceBetweenCities() {
        String isoInstant = "2020-08-20T21:35:11Z";
        LocalDateTime localDateTimeSaoPaulo = ofInstant(Instant.parse(isoInstant), zoneIdSaoPaulo);
        LocalDateTime localDateTimeBarcelona = ofInstant(Instant.parse(isoInstant), zoneIdBarcelona);

        // Call the normalized will ensure that a fixed offset ID will be represented as a ZoneOffset. API doc.
        ZoneId offsetBrazil = zoneIdSaoPaulo.getRules().getOffset(localDateTimeSaoPaulo).normalized();
        ZoneId offsetSpain = ZoneOffset.from(ZonedDateTime.of(localDateTimeBarcelona, zoneIdBarcelona)).normalized();

        assertEquals(ZoneOffset.ofHours(-3), offsetBrazil, "Is expected -03:00");
        assertEquals(ZoneOffset.ofHours(2), offsetSpain,"Is expected +02:00");

        Duration duration = Duration.between(localDateTimeBarcelona, localDateTimeSaoPaulo);
        Duration expectedDuration = Duration.ofHours(-5);
        assertEquals(expectedDuration, duration, "The duration expected is PT-5H");
    }


    @Test
    @DisplayName("List all zone ids available")
    void listAllAvailableZoneIds() {
        // Abstract class
        ArrayList<String> availableZoneIdsZoneId = new ArrayList<>(ZoneId.getAvailableZoneIds());
        // Child of ZoneId, calling the same method
        ArrayList<String> availableZoneIdsZoneOffSet = new ArrayList<>(ZoneOffset.getAvailableZoneIds());

        List<String> timeZoneId = Stream.of(TimeZone.getAvailableIDs())
                .map(TimeZone::getTimeZone)
                .map(TimeZone::getID)
                .collect(Collectors.toList());

        availableZoneIdsZoneId.removeAll(timeZoneId);
        short expectedSize = 0;
        assertEquals(expectedSize, availableZoneIdsZoneId.size());

        timeZoneId.removeAll(availableZoneIdsZoneOffSet);
        short expectedOlderUSTimezonesNames = 28;
        assertEquals(expectedOlderUSTimezonesNames, timeZoneId.size());
    }

    @Test
    @DisplayName("Transform UTC - Coordinated Universal Time in other Timezone")
    void transformUTCInOtherTimezone() {
        Clock systemUTC = Clock.systemUTC();
        // Or
        //Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeFromClock = ZonedDateTime.now(systemUTC);

        LocalDateTime barcelona = ofInstant(systemUTC.instant(), zoneIdBarcelona);
        LocalDateTime saoPaulo = ofInstant(systemUTC.instant(), zoneIdSaoPaulo);
        LocalDateTime tokyo = ofInstant(systemUTC.instant(), ZoneId.of("Asia/Tokyo").normalized());

        assertTrue(tokyo.isAfter(zonedDateTimeFromClock.toLocalDateTime()));
        assertTrue(zonedDateTimeFromClock.toLocalDateTime().isBefore(barcelona)); // Happen at 00:00 UTC
        assertTrue(barcelona.isAfter(saoPaulo)); // Because has the UTC + 02:00 in summer time
    }

}