package tech.costa.luiz.reactive.domain;

/**
 * The type User.
 */
public class User {

    private String id;
    private String name;
    private String email;

    private User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static final class UserBuilder {
        private String id;
        private String name;
        private String email;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(id, name, email);
        }
    }
}
