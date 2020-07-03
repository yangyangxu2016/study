package com.example.ehcache;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.TimeUnit;

public class CacheUtils {
























    public Object get(String key) {

        if (StringUtils.isBlank(key)) {
            return null;
        }

        if (CacheGlobal.concurrentMap.isEmpty()) {
            return null;
        }

        if (!CacheGlobal.concurrentMap.containsKey(key)) {
            return null;
        }
        CacheValue cache = CacheGlobal.concurrentMap.get(key);
        //惰性删除
        long timeoutTime = TimeUnit.NANOSECONDS.toSeconds(
                System.nanoTime() - cache.getWriteTime());
        if (cache.getExpireTime() <= timeoutTime) {
            CacheGlobal.concurrentMap.remove(key);
            return null;
        }
        cache.setHitCount(cache.getHitCount() + 1);
        cache.setLastTime(System.currentTimeMillis());
        return cache.getValue();
    }
}
