package tech.costa.luiz.reactive.infrastructure;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Translator {

    Dictionary dictionary;

    @Counted
    String translate(String sentence) {
        return null;
    }
}
