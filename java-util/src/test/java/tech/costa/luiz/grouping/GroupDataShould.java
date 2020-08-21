package tech.costa.luiz.grouping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.luiz.grouping.Champion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Group Data should.
 */
@DisplayName("How to group data")
class GroupDataShould {

    private static final String fileName = "src/test/resources/file/champions_league.txt";
    private List<String> lines;
    private List<Champion> champions;
    private Map<String, List<Champion>> winnersByNation;

    /**
     * Before each method.
     */
    @BeforeEach
    void beforeEachMethod() {
        readTheFile();
        createChampions();
        groupByNation();
    }

    /**
     * Group winners by nation
     */
    private void groupByNation() {
        winnersByNation = champions.stream().collect(Collectors.groupingBy(Champion::getWinnerNation));
    }

    /**
     * Create a Champion object
     * Skip the first line because the first line is the header
     */
    private void createChampions() {
        long linesToSkip = 1L;
        champions = lines.stream().skip(linesToSkip)
                .map(Champion::toChampion)
                .collect(Collectors.toList());
    }

    /**
     * Read the file.
     */
    void readTheFile() {
        final Path path = Paths.get(fileName);
        try (Stream<String> fileLines = Files.lines(path)) {
            lines = fileLines.collect(Collectors.toList());
        } catch (IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     * Group winner by team.
     */
    @Test
    @DisplayName("Group the winners by team")
    void groupWinnerByTeam() {
        final Map<String, List<Champion>> winnerByTeam =
                champions.stream()
                        .collect(Collectors.groupingBy(Champion::getWinnerTeam));

        int expectedRealMadrid = 13;
        int expectedLiverpool = 6;
        int expectedBarcelona = 5;

        assertEquals(expectedRealMadrid, winnerByTeam.get("Real Madrid").size());
        assertEquals(expectedLiverpool, winnerByTeam.get("Liverpool").size());
        assertEquals(expectedBarcelona, winnerByTeam.get("Barcelona").size());
    }

    /**
     * Group winners by nation.
     */
    @Test
    @DisplayName("Group the winners by nation")
    void groupWinnersByNation() {
        final Map<String, List<Champion>> byWinnerNation = champions.stream()
                .collect(Collectors.groupingBy(Champion::getWinnerNation));

        int winnersByNationExpected = 11;
        int expectedSpainWinners = 18;
        int expectedItalianWinners = 12;
        int expectedPortugalWinners = 4;
        int expectedYugoslaviaWinners = 1;

        assertEquals(winnersByNationExpected, byWinnerNation.size());
        assertEquals(expectedSpainWinners, byWinnerNation.get("ESP").size());
        assertEquals(expectedItalianWinners, byWinnerNation.get("ITA").size());
        assertEquals(expectedPortugalWinners, byWinnerNation.get("POR").size());
        assertEquals(expectedYugoslaviaWinners, byWinnerNation.get("YUG").size());
    }

    @Test
    @DisplayName("Group winners by Spain and add them to a list")
    void groupWinnersByNationAndAddToAList() {
        final HashMap<String, List<Champion>> championsByCountryAndClub = winnersByNation.get("ESP").stream()
                .collect(Collectors.groupingBy(Champion::getWinnerTeam, HashMap::new, Collectors.toList()));

        int expectedTitlesWonByRealMadrid = 13;
        int expectedTitlesWonByBarcelona = 5;

        assertAll(() -> {
            assertTrue(championsByCountryAndClub.containsKey("Barcelona"));
            assertTrue(championsByCountryAndClub.containsKey("Real Madrid"));
            assertFalse(championsByCountryAndClub.containsKey("Sabadell"));
            assertEquals(expectedTitlesWonByRealMadrid, championsByCountryAndClub.get("Real Madrid").size());
            assertEquals(expectedTitlesWonByBarcelona, championsByCountryAndClub.get("Barcelona").size());
        });
    }

    @Test
    @DisplayName("Group winners by Spain and count the titles")
    void groupWinnersByNationAndCountThem() {
        final Map<String, Long> spanishWinners = winnersByNation.get("ESP").stream()
                .collect(Collectors.groupingBy(Champion::getWinnerTeam, Collectors.counting()));

        assertAll(() -> {
            int winnersSize = 2;
            long wonByBarcelona = 5L;
            long wonByMadrid = 13L;

            assertEquals(winnersSize, spanishWinners.size());
            assertEquals(wonByBarcelona, spanishWinners.get("Barcelona"));
            assertEquals(wonByMadrid, spanishWinners.get("Real Madrid"));
        });
    }

    @Test
    @DisplayName("Group English winners and join the season to a variable")
    void groupWinnersByNationAndSeason() {
        final Map<String, String> englishWinners = winnersByNation.get("ENG").stream()
                .collect(Collectors.toMap(Champion::getWinnerTeam, Champion::getSeason,
                        (current, older) -> current + ", " + older));

        assertAll(() -> {
            int winnersSize = 5;
            String liverpoolSeasons = "1976–77, 1977–78, 1980–81, 1983–84, 2004–2005, 2018–2019";
            String chelseaSeasons = "2011–2012";

            assertEquals(winnersSize, englishWinners.size());
            assertEquals(liverpoolSeasons, englishWinners.get("Liverpool"));
            assertEquals(chelseaSeasons, englishWinners.get("Chelsea"));
            assertNull(englishWinners.get("Any club"));
        });
    }
}