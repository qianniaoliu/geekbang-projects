/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.management;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlong
 */
public class StandardMBeanDemo {

    public static List<Object> objectList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        while (true){
            objectList.add(new Object());
        }
    }
}