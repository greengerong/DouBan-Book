package com.github.greengerong.book.utils.cache;

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
public class NoneCacheManage implements CacheManage {
    @Override
    public <T> T get(Object key) {
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public CacheManage put(Object key, Object value) {
        return this;
    }

    @Override
    public CacheManage remove(Object key) {
        return this;
    }

    @Override
    public CacheManage clear() {
        return this;
    }
}
