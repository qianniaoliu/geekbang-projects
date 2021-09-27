/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.jpa;

import com.athena.sql.Person;
import org.apache.commons.dbcp.BasicDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.Date;
import java.util.Properties;

/**
 * @author shenlong
 * @version jpaDemo.java, v 0.1 2021年08月31日 7:40 下午 shenlong
 */
public class JpaDemo {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emf", getProperties());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Person person = new Person();
        person.setId(12);
        person.setName("shenlong12");
        person.setAddress("usa");
        person.setCreateTime(new Date());
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(person);
        transaction.commit();

        Person personResult = entityManager.find(Person.class, 12);
        System.out.println("find person: " + personResult);
    }


    private static Properties getProperties(){
        Properties result = new Properties();
        result.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        result.put("hibernate.id.new_generator_mappings", false);
        result.put("hibernate.connection.datasource", getDataSource());
        return result;
    }

    private static DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/geekbang-projects");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
}