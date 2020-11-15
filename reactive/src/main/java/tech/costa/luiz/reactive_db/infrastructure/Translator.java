package tech.costa.luiz.reactive_db.infrastructure;

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
