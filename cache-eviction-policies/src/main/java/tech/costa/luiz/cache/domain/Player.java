package tech.costa.luiz.cache.domain;

import java.util.Objects;

/**
 * The type Player.
 */
public class Player {

    private final String id;
    private final String name;

    /**
     * Instantiates a new Player.
     *
     * @param id   the id
     * @param name the name
     */
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * The type Player builder.
     */
    public static final class PlayerBuilder {
        private String id;
        private String name;

        private PlayerBuilder() {
        }

        /**
         * A player player builder.
         *
         * @return the player builder
         */
        public static PlayerBuilder aPlayer() {
            return new PlayerBuilder();
        }

        /**
         * With id player builder.
         *
         * @param id the id
         * @return the player builder
         */
        public PlayerBuilder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * With name player builder.
         *
         * @param name the name
         * @return the player builder
         */
        public PlayerBuilder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Build player.
         *
         * @return the player
         */
        public Player build() {
            return new Player(id, name);
        }
    }
}
