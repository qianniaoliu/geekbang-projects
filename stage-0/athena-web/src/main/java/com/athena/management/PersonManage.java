/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.management;

import com.athena.sql.Person;

import java.util.Date;

/**
 * @author shenlong
 * @version PersonManage.java, v 0.1 2021年10月31日 10:57 下午 shenlong
 */
public class PersonManage implements PersonManageMBean{

    private Person person;

    public PersonManage(Person person) {
        this.person = person;
    }

    @Override
    public Integer getId() {return person.getId();}

    @Override
    public void setId(Integer id) {person.setId(id);}

    @Override
    public String getName() {return person.getName();}

    @Override
    public void setName(String name) {person.setName(name);}

    @Override
    public String getAddress() {return person.getAddress();}

    @Override
    public void setAddress(String address) {person.setAddress(address);}

    @Override
    public Date getCreateTime() {return person.getCreateTime();}

    @Override
    public void setCreateTime(Date createTime) {person.setCreateTime(createTime);}
}