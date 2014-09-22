package com.github.greengerong.book.utils.cache;

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
public interface CacheManage {
    
    <T> T get(Object key);

    boolean containsKey(Object key);

    CacheManage put(Object key, Object value);

    CacheManage putAll(Map map);

    CacheManage remove(Object key);

    CacheManage removeAll(Iterable<?> keys);

    CacheManage clear();
}
