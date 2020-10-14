package tech.costa.luiz.cache.strategy.lfu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.dataset.NewsDataSet;
import tech.costa.luiz.cache.domain.News;
import tech.costa.luiz.cache.domain.Platform;
import tech.costa.luiz.cache.domain.SocialMedia;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Least frequently used test.
 */
@DisplayName("LFU")
class LeastFrequentlyUsedTest {

    /**
     * The Capacity.
     */
    int capacity = 3;

    /**
     * Remove the less accessed.
     */
    @DisplayName("Counts how often an item is needed. Those that are used least often are discarded first.")
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
        final Map<News, LeastFrequentlyUsed<News, SocialMedia>.CountItem> countItemMap = cache.getCountItemMap();
        final int countSize = countItemMap.size();

        assertThat(actualExpected, is(equalTo(cache.size())));
        assertThat(countSize, is(equalTo(cache.size())));

        assertAll(() -> {
            assertTrue(countItemMap.containsKey(dataSet.getRonaldo()));
            assertThat(countItemMap.get(dataSet.getRonaldo()).getCount(), is(equalTo(0)));
            assertTrue(countItemMap.containsKey(dataSet.getMessi()));
            assertThat(countItemMap.get(dataSet.getMessi()).getCount(), is(equalTo(2)));
            assertTrue(countItemMap.containsKey(dataSet.getRakitic()));
            assertThat(countItemMap.get(dataSet.getRakitic()).getCount(), is(equalTo(1)));
        });
    }
}