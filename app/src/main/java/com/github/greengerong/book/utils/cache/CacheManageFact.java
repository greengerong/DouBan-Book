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
public final class CacheManageFact {

    public static CacheManage getNoneCache() {
        return new NoneCacheManage();
    }

    public static CacheManage getDefaultCache() {
        return new DefaultCacheManage();
    }

    public static CacheManage getLruCache() {
        return new LRUCacheManage();
    }
}
