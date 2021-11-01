/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.management;

import com.athena.sql.Person;

import javax.management.MBeanInfo;
import javax.management.StandardMBean;

/**
 * @author shenlong
 * @version StandardMBeanDemo.java, v 0.1 2021年11月01日 12:57 上午 shenlong
 */
public class StandardMBeanDemo {
    public static void main(String[] args) throws Exception {
        StandardMBean standardMBean = new StandardMBean(new PersonManage(new Person()), PersonManageMBean.class);
        MBeanInfo mBeanInfo = standardMBean.getMBeanInfo();
        System.out.println(mBeanInfo);
    }
}