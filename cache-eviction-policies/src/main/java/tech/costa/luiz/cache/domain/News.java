package tech.costa.luiz.cache.domain;

public class News {
    private String title;
    private Source source;

    /**
     * Instantiates a new News.
     *
     * @param title  the title
     * @param source the source
     */
    public News(String title, Source source) {
        this.title = title;
        this.source = source;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets source.
     *
     * @param source the source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
