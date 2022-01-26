/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.reactive.stream;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author shenlong
 * @version DefaultSubscriber.java, v 0.1 2021��12��29�� 7:55 ���� shenlong
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
        System.out.println("���յ���Ϣ:" + t);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("���յ�����");

    }

    @Override
    public void onComplete() {
        System.out.println("��Ϣ�������");
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