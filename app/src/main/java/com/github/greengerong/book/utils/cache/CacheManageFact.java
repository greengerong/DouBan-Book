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
    public static final CacheManage NONE_CACHE = new NoneCacheManage();
    public static final CacheManage DEFAULT_CACHE = new DefaultCacheManage();
    public static final CacheManage LRU_CACHE = new LRUCacheManage();
}
