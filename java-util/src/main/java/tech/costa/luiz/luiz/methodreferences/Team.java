package tech.costa.luiz.luiz.methodreferences;

import java.util.List;

public class Team implements Club {

    private List<String> players;

    public Team(List<String> players) {
        this.players = players;
    }

    public static Team of(List<String> players) {
        return new Team(players);
    }

    @Override
    public List<String> players() {
        // Not a good practice returning this way
        // I'll write a tutorial this why
        return players;
    }

    public String joinPlayers() {
        return String.join(",", players).toUpperCase();
    }

    public Team welcomeMessage() {
        players.forEach(player -> System.out.println("Hello  "+player));
        return this;
    }

    @Override
    public String toString() {
        return "Team{" +
                "players=" + players +
                '}';
    }
}
