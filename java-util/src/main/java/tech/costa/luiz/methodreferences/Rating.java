package tech.costa.luiz.methodreferences;

import java.util.Optional;

import static java.util.Objects.isNull;

public enum Rating {

    EXCELLENT, GOOD , OK;

    static Rating rating(Team team) {
        if (isNull(team)) {
            return OK;
        } else {
            final Optional<String> hasJensonButton = team.players().stream()
                    .filter(player -> player.contains("Button")).findAny();
            if (hasJensonButton.isPresent()) {
                return GOOD;
            }
            return EXCELLENT;
        }
    }
}
