/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.sql;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author shenlong
 * @version DBConnectionManager.java
 */
public class DBConnectionManager {

    public static void main(String[] args) {
        String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/geekbang-projects";
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(mysqlUrl, "root", "123456");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE user(id int(10), name varchar(255), address varchar(255), create_time datetime)");
            statement.execute("INSERT INTO user(id, name, address, create_time) VALUES(1, 'yumu', 'chengdu', '2021-08-31 12:00:00')");
            ResultSet resultSet = statement.executeQuery("SELECT id, name, address, create_time FROM user");
            ResultSetMetaData metaData = resultSet.getMetaData();
            BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
            Map<String, String> columnNameMapping = new HashMap<>();
            for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                String columnName = metaData.getColumnLabel(columnIndex);
                columnNameMapping.put(convertHump(columnName), columnName);
            }
            while (resultSet.next()) {
                Object resultValue = Person.class.getDeclaredConstructor().newInstance();
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
                    Class propertyType = propertyDescriptor.getPropertyType();
                    String getMethodName = typeMethodNameMapping.get(propertyType);
                    try {
                        Method method = ResultSet.class.getMethod(getMethodName, String.class);
                        Object result = method.invoke(resultSet, columnNameMapping.get(propertyDescriptor.getName()));
                        Method setterMethod = propertyDescriptor.getWriteMethod();
                        setterMethod.invoke(resultValue, result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("current data:" + resultValue);
            }

        } catch (Exception ex) {
            System.out.println("execute sql error");
        } finally {
            //try {
            //    statement.execute("DROP TABLE user");
            //} catch (SQLException throwables) {
            //    throwables.printStackTrace();
            //}
        }

    }

    private final static Map<Class, String> typeMethodNameMapping = new HashMap<>();

    static {
        typeMethodNameMapping.put(Integer.class, "getInt");
        typeMethodNameMapping.put(String.class, "getString");
        typeMethodNameMapping.put(Date.class, "getDate");
    }

    private static String convertHump(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(source.toLowerCase());
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}