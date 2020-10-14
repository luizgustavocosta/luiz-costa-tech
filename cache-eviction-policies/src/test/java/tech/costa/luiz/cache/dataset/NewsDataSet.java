package tech.costa.luiz.cache.dataset;

import tech.costa.luiz.cache.domain.News;
import tech.costa.luiz.cache.domain.Source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Data set.
 */
public class NewsDataSet {

    private static final NewsDataSet INSTANCE;

    static {
        INSTANCE = new NewsDataSet();
    }

    private final News suarez;
    private final News messi;
    private final News rakitic;
    private final News pjanic;
    private final News ronaldo;
    private List<News> news = new ArrayList<>();

    /**
     * Private constructor
     */
    private NewsDataSet() {
        suarez = new News("Luis Suarez deserves the utmost respect", Source.MARCA);
        rakitic = new News("Rakitic wants to fulfil his Barcelona contract", Source.BBC);
        messi = new News("Messi scores 10 goals in one match", Source.AS);
        pjanic = new News("Pjanic ha detto sì: accordo col Barça. " +
                "La Juve vuole Arthur, ma i catalani offrono Rakitic o Vidal", Source.BBC);
        ronaldo = new News("Cristiano Ronaldo for the Ballon d'Or?", Source.LA_GAZZETTA_DELLO_SPORT);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static NewsDataSet getInstance() {
        return INSTANCE;
    }

    /**
     * Build initial cache news list.
     *
     * @return the list
     */
    public List<News> buildInitialCacheNews() {
        news = Arrays.asList(getSuarez(), getMessi(), getRakitic());
        return news;
    }

    /**
     * Build all news list.
     *
     * @return the list
     */
    public List<News> buildAllNews() {
        news = Arrays.asList(getSuarez(),
                getMessi(), getRakitic(), getRakitic(), getRonaldo());

        return news;
    }

    /**
     * Gets suarez.
     *
     * @return the suarez
     */
    public News getSuarez() {
        return suarez;
    }

    /**
     * Gets messi.
     *
     * @return the messi
     */
    public News getMessi() {
        return messi;
    }

    /**
     * Gets rakitic.
     *
     * @return the rakitic
     */
    public News getRakitic() {
        return rakitic;
    }

    /**
     * Gets pjanic.
     *
     * @return the pjanic
     */
    public News getPjanic() {
        return pjanic;
    }

    /**
     * Gets ronaldo.
     *
     * @return the ronaldo
     */
    public News getRonaldo() {
        return ronaldo;
    }
}
