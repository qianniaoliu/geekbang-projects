/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.reactive.stream;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author shenlong
 * @version DefaultSubscriber.java, v 0.1 2021年12月29日 7:55 下午 shenlong
 */
public class DefaultSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;

    private int count;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
    }

    @Override
    public void onNext(T t) {
        if (++count == 3) {
            subscription.cancel();
            return;
        }
        System.out.println("接收到消息:" + t);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("接收到错误");

    }

    @Override
    public void onComplete() {
        System.out.println("消息处理完成");
    }

    /**
     * Getter method for property <tt>subscription</tt>.
     *
     * @return property value of subscription
     */
    public DefaultSubscription getSubscription() {
        return (DefaultSubscription) subscription;
    }
}