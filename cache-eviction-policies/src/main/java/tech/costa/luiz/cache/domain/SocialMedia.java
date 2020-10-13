package tech.costa.luiz.cache.domain;

/**
 * The type Social media.
 */
public class SocialMedia {

    private final Platform Platform;

    /**
     * Instantiates a new Social media.
     *
     * @param Platform the Platform
     */
    public SocialMedia(Platform Platform) {
        this.Platform = Platform;
    }

    /**
     * Gets Platform.
     *
     * @return the Platform
     */
    public Platform getPlatform() {
        return Platform;
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
                "Platform=" + Platform +
                '}';
    }
}
