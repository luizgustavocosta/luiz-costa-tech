package tech.costa.luiz.reactive_db;

import org.springframework.context.ApplicationEvent;

public class ExampleCreatedEvent extends ApplicationEvent {

    public ExampleCreatedEvent(Example event) {
        super(event);
    }
}
