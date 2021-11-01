/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.management;

import java.util.Date;

/**
 * @author shenlong
 */
public interface PersonManageMBean {
    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    Date getCreateTime();

    void setCreateTime(Date createTime);
}