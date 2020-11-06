package tech.costa.luiz.reactive.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.atomic.AtomicLong;

public class AuctionRepository {

    AtomicLong atomicLong = new AtomicLong(0);

    public @NonNull Observable<Object> findInfinityAuctions() {
        return Observable.create(emitter -> {
            while(true) {
                emitter.onNext(atomicLong.incrementAndGet());
            }
        });
    }

    public @NonNull Flowable<Integer> findTop10Auctions() {
        return Flowable.range(1, 10)
                .flatMap(value ->
                        Flowable.just(value)
                                .subscribeOn(Schedulers.computation())
                                .map(mapper -> mapper * mapper)
                );
    }
}
