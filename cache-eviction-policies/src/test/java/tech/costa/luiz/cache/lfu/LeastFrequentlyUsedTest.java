package tech.costa.luiz.cache.lfu;

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

/**
 * The type Least frequently used test.
 */
class LeastFrequentlyUsedTest {

    /**
     * The Capacity.
     */
    int capacity = 3;

    /**
     * Remove the less accessed.
     */
    @Test
    void remove_the_less_accessed() {
        LeastFrequentlyUsed<News, SocialMedia> cache = new LeastFrequentlyUsed<>(capacity);
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
        assertThat(actualExpected, is(equalTo(cache.size())));
        final List<String> lfuCache = cache.getCache()
                .entrySet().stream()
                .map(news -> String.join(",", news.getKey().getTitle(), news.getValue().getPlatform().name()))
                .collect(Collectors.toList());


        List<String> expectedLines = Arrays.asList(
                "Messi scores 10 goals in one match,TWITTER",
                "Cristiano Ronaldo for the Ballon d'Or?,INSTAGRAM",
                "Rakitic wants to fulfil his Barcelona contract,INSTAGRAM");

        assertLinesMatch(expectedLines, lfuCache);
    }
}