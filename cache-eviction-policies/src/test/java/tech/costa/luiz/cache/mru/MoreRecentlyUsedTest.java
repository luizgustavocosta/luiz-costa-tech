package tech.costa.luiz.cache.mru;

import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.NewsDataSet;
import tech.costa.luiz.cache.domain.News;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type More recently used test.
 */
class MoreRecentlyUsedTest {

    /**
     * Discard first cached element.
     */
    @Test
    @SuppressWarnings("")
    void discard_first_cached_element() {
        int cacheSize = 3;
        MoreRecentlyUsed<News> cacheStrategy = new MoreRecentlyUsed<>(cacheSize);
        // Given
        final NewsDataSet dataSet = NewsDataSet.getInstance();

        cacheStrategy.add(dataSet.getSuarez())
                .add(dataSet.getMessi())
                .add(dataSet.getRakitic());

        final News newsForMessi = cacheStrategy.get(dataSet.getMessi());
        assertEquals(newsForMessi, dataSet.getMessi());

        // When
        cacheStrategy.add(dataSet.getPjanic());
        cacheStrategy.get(dataSet.getPjanic());
        cacheStrategy.add(dataSet.getRonaldo());

        // Then
        assertThat(cacheSize, is(equalTo(cacheStrategy.size())));
        // FIXME add more asserts
    }
}