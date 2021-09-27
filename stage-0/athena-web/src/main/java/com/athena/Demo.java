/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena;

import org.jboss.logging.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenlong
 * @version Demo.java, v 0.1 2021年09月27日 9:08 下午 shenlong
 */
public class Demo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        AtomicInteger traceId = new AtomicInteger(0);
        while (true){
            executorService.execute(() -> {
                Integer num = traceId.incrementAndGet();
                MDC.put("traceId", num);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(!num.equals(MDC.get("traceId"))){
                    throw new RuntimeException();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                MDC.clear();

            });

        }
    }
}