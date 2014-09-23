package com.github.greengerong.book.utils.cache;

import android.util.LruCache;

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
public class LRUCacheManage implements CacheManage {
    private LruCache<Object, Object> cahce;

    public LRUCacheManage() {
        cahce = new LruCache<Object, Object>(100);
    }

    @Override
    public <T> T get(Object key) {
        final Object value = cahce.get(key);
        return value == null ? null : (T) value;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public CacheManage put(Object key, Object value) {
        cahce.put(key, value);
        return this;
    }

    @Override
    public CacheManage remove(Object key) {
        cahce.remove(key);
        return this;
    }

    @Override
    public CacheManage clear() {
        cahce.evictAll();
        return this;
    }
}
