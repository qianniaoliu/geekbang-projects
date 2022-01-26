/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.reactive.stream;

import org.reactivestreams.Subscription;

/**
 * @author shenlong
 * @version DefaultSubscription.java, v 0.1 2021年12月29日 8:03 下午 shenlong
 */
public class DefaultSubscription implements Subscription {

    private boolean canceled;

    private long num;

    @Override
    public void request(long n) {
        this.num = n;
    }

    @Override
    public void cancel() {
        this.canceled = true;
    }

    /**
     * Getter method for property <tt>canceled</tt>.
     *
     * @return property value of canceled
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Getter method for property <tt>num</tt>.
     *
     * @return property value of num
     */
    public long getNum() {
        return num;
    }
}