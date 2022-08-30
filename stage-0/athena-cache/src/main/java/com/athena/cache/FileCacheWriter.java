/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;
import java.io.FileOutputStream;
import java.util.Collection;

/**
 * @author shenlong
 */
public class FileCacheWriter<K, V> implements CacheWriter<K, V> {

    private final static String FILE_DATABASE_PATH = "META-INF/file-database.properties";

    @Override
    @SneakyThrows
    public void write(Entry<? extends K, ? extends V> entry) throws CacheWriterException {
        @Cleanup
        FileOutputStream outputStream = new FileOutputStream(FILE_DATABASE_PATH);
        IOUtils.write("\nHello,World", outputStream);

    }

    @Override
    public void writeAll(Collection<Entry<? extends K, ? extends V>> entries) throws CacheWriterException {

    }

    @Override
    public void delete(Object key) throws CacheWriterException {

    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {

    }
}