/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.util;

import java.util.Objects;

/**
 * @author shenlong
 */
public class AssetUtils {

    public static void assetNotNull(Object obj){
        if(Objects.isNull(obj)) {
            throw new NullPointerException("obj is not null");
        }
    }
}