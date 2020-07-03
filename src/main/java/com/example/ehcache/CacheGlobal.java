package com.example.ehcache;

import sun.misc.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 缓存全局类
 *
 * @author xuyy
 */
public class CacheGlobal {

    public static ConcurrentMap<String, CacheValue> concurrentMap = new ConcurrentHashMap<>();


}
