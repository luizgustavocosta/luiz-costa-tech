package tech.costa.luiz.reactive_db;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
class ExampleCreatedEventPublisher implements
        ApplicationListener<ExampleCreatedEvent>,
        Consumer<FluxSink<ExampleCreatedEvent>> {

    private final Executor executor;
    private final BlockingQueue<ExampleCreatedEvent> queue =
            new LinkedBlockingQueue<>();

    @Override
    public void onApplicationEvent(ExampleCreatedEvent event) {
        this.queue.offer(event);
    }

    @Override
    public void accept(FluxSink<ExampleCreatedEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    ExampleCreatedEvent event = queue.take();
                    sink.next(event);
                } catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}
