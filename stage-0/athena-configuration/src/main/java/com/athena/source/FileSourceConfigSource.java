/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.source;

import lombok.Cleanup;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlong
 */
public class FileSourceConfigSource implements ConfigSource {

    private final static String RESOURCE_PATH = "META-INF/microprofile-config.properties";

    private final Logger logger = LoggerFactory.getLogger(FileSourceConfigSource.class);

    private final Map<String, String> configData = new ConcurrentHashMap<>();

    @Override
    public Set<String> getPropertyNames() {
        return configData.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        if(configData.isEmpty()){
            ClassLoader classLoader = getClass().getClassLoader();
            try {
                @Cleanup
                InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_PATH);
                Properties properties = new Properties();
                properties.load(inputStream);
                properties.forEach((key, val) -> {
                    configData.put(String.valueOf(key), String.valueOf(val));
                });
            } catch (Exception ex) {
                logger.error("读取配置文件数据失败", ex);
            }
        }
        return configData.get(propertyName);
    }

    @Override
    public String getName() {
        return FileSourceConfigSource.class.getName();
    }
}