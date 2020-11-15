package tech.costa.luiz.reactive_db.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserRepository {

    public static void main(String[] args) {
        x();
    }

    public static void x() {
        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
    }
}
