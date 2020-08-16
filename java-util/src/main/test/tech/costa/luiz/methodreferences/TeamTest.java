package tech.costa.luiz.methodreferences;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void acceptByConstructor() {
        // By functional interface
        Club emptyClubByConstructor = ArrayList::new;
        assertNotNull(emptyClubByConstructor, "This object should not be null");

        String[] players = {"Pelé", "Maradona", "Romário", "Messi", "Ronaldo", "Ronaldinho"};

        // Using method reference to capitalize names and method references to ArrayList constructor
        final List<String> teamsCapitalized = Stream.of(players)
                .map(String::toUpperCase)
                .collect(toCollection(ArrayList::new));

        Team teams = new Team(teamsCapitalized);
        List<String> expectedSoccerTeams = Arrays.asList("PELÉ", "MARADONA", "ROMÁRIO", "MESSI", "RONALDO", "RONALDINHO");

        assertLinesMatch(expectedSoccerTeams, teamsCapitalized);
        // Unnecessary call the players method, just to didactic reasons
        assertLinesMatch(expectedSoccerTeams, teams.players());
    }

    @Test
    void acceptByInstanceMethods() {
        List<String> firstList = Arrays.asList("FCBarcelona", "Espanyol");
        List<String> secondList = Arrays.asList("Corinthians", "Santos");
        final List<Team> teams = Stream.of(firstList, secondList)
                // Call the constructor passing the list
                .map(Team::new)
                .collect(Collectors.toList());

        int expectedTeams = 2;
        assertEquals(expectedTeams, teams.size());

        // Create a list of String then transform into a list of integers to sum
        // The method Integer.valueOf represents the instance method
        List<String> numbers = Arrays.asList("1","2","3","5","7","11");
        final int sumOfList = numbers.stream()
                .mapToInt(Integer::valueOf)
                .sum();

        int expectedSum = 29;
        assertEquals(expectedSum, sumOfList, "There numbers should be equals");

    }

    @Test
    void acceptByStaticMethods() {
        final List<Rating> ratingPilotList = Stream.of(Arrays.asList("Lewis Hamilton", "Valtteri Bottas"),
                Arrays.asList("Lewis Hamilton", "Jenson Button"))
                // Calling the constructor passing the list
                .map(Team::new)
                // Calling the famous sysout with static println
                .peek(System.out::println)
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