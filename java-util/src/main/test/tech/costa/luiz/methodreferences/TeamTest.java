package tech.costa.luiz.methodreferences;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void acceptByConstructor() {
        // By functional interface
        Club emptyClubByConstructor = ArrayList::new;
        assertNotNull(emptyClubByConstructor, "This object should not be null");

        List<String> firstList = Arrays.asList("FCBarcelona", "Espanyol");
        List<String> secondList = Arrays.asList("Corinthians", "Santos");
        final List<Team> teams = Stream.of(firstList, secondList)
                // Call the constructor passing the list
                .map(Team::new)
                .collect(Collectors.toList());

        int expectedTeams = 2;
        assertEquals(expectedTeams, teams.size());
    }

    @Test
    void acceptByInstanceMethods() {

        final Optional<String> players = Stream.of(Arrays.asList("Player 1", "Player 2"))
                .map(Team::new)
                .map(Team::joinPlayers)
                .findAny();

        assertAll(() -> {
            assert(players.get().contains("PLAYER"));
        });

        final List<Team> otherPlayers = Stream.of(Arrays.asList("Mileto", "Tolomeo"))
                .map(Team::new)
                .map(Team::welcomeMessage)
                .collect(toList());

        assertFalse(otherPlayers.isEmpty());
    }

    @Test
    void acceptByStaticMethods() {
        final List<Rating> ratingPilotList = Stream.of(Arrays.asList("Lewis Hamilton", "Valtteri Bottas"),
                Arrays.asList("Lewis Hamilton", "Jenson Button"))
                // Calling a static builder
                .map(Team::of)
                // Calling a static method from Enum
                .map(Rating::rating)
                .collect(toList());


        List<Rating> ratingExpectedList = Arrays.asList(Rating.EXCELLENT, Rating.GOOD);
        assertLinesMatch(ratingToString(ratingExpectedList), ratingToString(ratingPilotList),
                "The rating should be EXCELLENT, GOOD");
    }

    private List<String> ratingToString(List<Rating> ratingList) {
        return ratingList.stream().map(Rating::toString).collect(toList());
    }
}