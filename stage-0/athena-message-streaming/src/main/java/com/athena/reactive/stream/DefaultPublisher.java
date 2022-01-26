/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.reactive.stream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlong
 * @version DefaultPublisher.java, v 0.1 2021年12月29日 3:44 下午 shenlong
 */
public class DefaultPublisher<T> implements Publisher<T> {

    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber<? super T> s) {
        Subscription subscription = new DefaultSubscription();
        s.onSubscribe(subscription);
        subscribers.add(s);
    }

    public void publish(T data) {
        subscribers.forEach(subscriber -> {
            if (DefaultSubscriber.class.cast(subscriber).getSubscription().isCanceled()) {
                return;
            }
            subscriber.onNext(data);
        });
    }
}