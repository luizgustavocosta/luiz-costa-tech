package tech.costa.luiz.cache.lru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.NewsDataSet;
import tech.costa.luiz.cache.domain.News;
import tech.costa.luiz.cache.domain.Platform;
import tech.costa.luiz.cache.domain.SocialMedia;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class LeastRecentlyUsedTest {

    LeastRecentlyUsed<String, String> lru;
    int capacity = 3;

    @BeforeEach
    void setUp() {
        lru = new LeastRecentlyUsed<>(3);
    }

    @Test
    void remove_the_less_accessed() {
        LeastRecentlyUsed<News, SocialMedia> cache = new LeastRecentlyUsed<>(capacity);

        // Given
        final NewsDataSet dataSet = NewsDataSet.getInstance();

        dataSet.buildInitialCacheNews()
                .forEach(news -> {
                    Platform platform = Platform.INSTAGRAM;
                    if (news.getTitle().contains("Messi")) {
                        platform = Platform.TWITTER;
                    } else if (news.getTitle().contains("Suarez")) {
                        platform = Platform.FACEBOOK;
                    }
                    cache.put(news, new SocialMedia(platform));
                });

        // When
        cache.get(dataSet.getMessi());
        cache.get(dataSet.getMessi());
        cache.get(dataSet.getRakitic());

        cache.put(dataSet.getPjanic(), new SocialMedia(Platform.INSTAGRAM));
        cache.put(dataSet.getRonaldo(), new SocialMedia(Platform.INSTAGRAM));

        int actualExpected = 3;

        // Then
        assertThat(actualExpected, is(equalTo(cache.getCache().size())));
        final List<String> lru = cache.getCache()
                .entrySet().stream()
                .map(news -> String.join(",", news.getKey().getTitle(), news.getValue().getPlatform().name()))
                .collect(Collectors.toList());


        List<String> expectedLines = Arrays.asList(
                "Rakitic wants to fulfil his Barcelona contract,INSTAGRAM",
                "Pjanic ha detto sì: accordo col Barça. La Juve vuole Arthur, ma i catalani offrono Rakitic o Vidal,INSTAGRAM",
                "Cristiano Ronaldo for the Ballon d'Or?,INSTAGRAM");

        assertLinesMatch(expectedLines, lru);
    }
}