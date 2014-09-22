package com.github.greengerong.book.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2014                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class DefaultCacheManage implements CacheManage {
    private static CacheManage instance;
    private static final Object lockObj = new Object();
    private Cache<Object, Object> cahce;

    private DefaultCacheManage() {
        cahce = CacheBuilder.newBuilder().maximumSize(100).build();
    }

    public static CacheManage getInstance() {
        if (instance == null) {
            synchronized (lockObj) {
                if (instance == null) {
                    instance = new DefaultCacheManage();
                }
            }
        }
        return instance;
    }

    @Override
    public <T> T get(Object key) {
        final Object value = cahce.getIfPresent(key);
        return value == null ? null : (T) value;
    }

    @Override
    public boolean containsKey(Object key) {
        return cahce.getIfPresent(key) != null;
    }

    @Override
    public CacheManage put(Object key, Object value) {
        cahce.put(key, value);
        return this;
    }

    @Override
    public CacheManage putAll(Map map) {
        cahce.putAll(map);
        return this;
    }

    @Override
    public CacheManage remove(Object key) {
        cahce.invalidate(key);
        return this;
    }

    @Override
    public CacheManage removeAll(Iterable<?> keys) {
        cahce.invalidate(keys);
        return this;
    }

    @Override
    public CacheManage clear() {
        cahce.invalidateAll();
        return this;
    }
}
