/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.reactive.stream;

/**
 * @author shenlong
 * @version Demo.java, v 0.1 2021��12��29�� 8:24 ���� shenlong
 */
public class Demo {

    public static void main(String[] args) {
        DefaultPublisher publisher = new DefaultPublisher();

        publisher.subscribe(new DefaultSubscriber());

        for(int i = 0 ;i < 8; i++){
            publisher.publish(i);
        }
    }
}