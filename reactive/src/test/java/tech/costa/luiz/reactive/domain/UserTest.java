package tech.costa.luiz.reactive.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * The type User test.
 */
@DisplayName("User record")
class UserTest implements WithAssertions {

    /**
     * Email provider stream.
     *
     * @return the stream
     */
    static Stream<Arguments> emailProvider() {
        return Stream.of(
                Arguments.arguments("name?last@provider.com"),
                Arguments.arguments("name'last@provider.com"),
                Arguments.arguments("name@provider.com"),
                Arguments.arguments("name@provider.com.br")
        );
    }

    /**
     * Should allow valid emails.
     *
     * @param email the email
     */
    @ParameterizedTest(name = "Allow email {0}")
    @MethodSource("emailProvider")
    void shouldAllowValidEmails(String email)  {
        final User user = User.UserBuilder.anUser().withName("John Doe").withEmail(email).build();

        assertAll(() -> {
            assertThat(user).as("Expected an instance").isNotNull();
            assertThat(user.getEmail()).as("E-mail should be set").isNotNull();
        });
    }
}