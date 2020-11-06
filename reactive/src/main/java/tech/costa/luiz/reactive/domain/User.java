package tech.costa.luiz.reactive.domain;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

/**
 * The type User.
 */
public record User(String id, String name, String email) {

    // Pattern for RFC 5322
    private static final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    /**
     * Instantiates a new User.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     */
    public User {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            id = isNull(id) ? UUID.randomUUID().toString() : id;
        } else {
            throw new IllegalArgumentException("Invalid e-mail "+email);
        }
    }

}
