package tech.costa.luiz.reactive_db.infrastructure;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class Producers {

    @Produces
    double pi = Math.PI;

    @Produces
    List<String> getNames() {
        return Arrays.asList("Andy", "Adalbert", "Joachim");
    }
}
