/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.common.function;

/**
 * @author shenlong
 * @version ThrowAction.java, v 0.1 2022��03��16�� 8:01 ���� shenlong
 */
@FunctionalInterface
public interface ThrowAction {
    void execute() throws Throwable;


    static void execute(ThrowAction throwAction){
        try {
            throwAction.execute();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}