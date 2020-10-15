package tech.costa.luiz.cache.dataset;


import tech.costa.luiz.cache.domain.Id;
import tech.costa.luiz.cache.domain.Player;

/**
 * The type Player data set.
 */
public class PlayerDataSet {

    /**
     * Gets coutinho.
     *
     * @return the coutinho
     */
    public static Player getCoutinho() {
        return Player.PlayerBuilder.aPlayer()
                .withId(Id.getInstance().newId())
                .withName("Coutinho")
                .build();
    }

    /**
     * Gets neto.
     *
     * @return the neto
     */
    public static Player getNeto() {
        return Player.PlayerBuilder.aPlayer()
                .withId(Id.getInstance().newId())
                .withName("Neto")
                .build();
    }

    /**
     * Gets messi.
     *
     * @return the messi
     */
    public static Player getMessi() {
        return Player.PlayerBuilder.aPlayer()
                .withId(Id.getInstance().newId())
                .withName("Messi")
                .build();
    }

    /**
     * Gets ronaldinho.
     *
     * @return the ronaldinho
     */
    public static Player getRonaldinho() {
        return Player.PlayerBuilder.aPlayer()
                .withId(Id.getInstance().newId())
                .withName("Ronaldinho")
                .build();
    }

    /**
     * Gets ter stegen.
     *
     * @return the ter stegen
     */
    public static Player getTerStegen() {
        return Player.PlayerBuilder.aPlayer()
                .withId(Id.getInstance().newId())
                .withName("Ter stegen")
                .build();
    }
}
