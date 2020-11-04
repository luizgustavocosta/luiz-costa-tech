package tech.costa.luiz.records;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The type Disc test.
 */
@DisplayName("Disc test")
class DiscTest {

    /**
     * Should create a record.
     */
    @Test void shouldCreateARecord() {
        final Disc newDisc = new Disc("WEA – BR 28.100",
                "Vinyl, LP, Album, Stereo",
                "Brazil",
                1984,
                "Rock, Pop",
                "New Wave, Pop Rock");

        assertNotNull(newDisc, "Is expected a new instance of disc");
    }

    /**
     * Should throw an exception when year is invalid.
     */
    @Test void shouldThrowAnExceptionWhenYearIsInvalid() {
        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Disc("Parlophone – 0190295209414",
                "Vinyl, LP, Album, Limited Edition, Orange Neon",
                "Europe",
                1400,
                "Electronic",
                "Dance-pop"));

        assertThat(exception, instanceOf(IllegalArgumentException.class));
        assertThat(exception.getMessage(), containsString("Invalid release year"));
    }

}