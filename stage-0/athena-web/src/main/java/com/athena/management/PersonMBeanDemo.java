/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.management;

import com.athena.sql.Person;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlong
 */
public class PersonMBeanDemo {
    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.athena.projects.person.management:type=PersonManage");
        Person person = new Person();
        mBeanServer.registerMBean(new PersonManage(person), objectName);
        while (true){
            System.out.println(person);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}