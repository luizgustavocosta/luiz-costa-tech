package tech.costa.luiz.cache.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Id.
 */
public class Id {

    private static final Id INSTANCE;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    static {
        INSTANCE = new Id();
    }
    
    private Id(){}

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Id getInstance() {
        return INSTANCE;
    }

    /**
     * New id string.
     *
     * @return the string
     */
    public String newId() {
        return String.valueOf(atomicInteger.incrementAndGet());
    }
}
