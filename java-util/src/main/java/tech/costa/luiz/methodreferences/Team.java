package tech.costa.luiz.methodreferences;

import java.util.ArrayList;
import java.util.List;

public class Team implements Club {

    private List<String> players = new ArrayList<>();

    public Team(List<String> players) {
        this.players = players;
    }

    public static String  doStuff() {
        return "X";
    }

    @Override
    public List<String> players() {
        // Not a good practice returning this way
        // I'll write a tutorial this why
        return players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "players=" + players +
                '}';
    }
}
