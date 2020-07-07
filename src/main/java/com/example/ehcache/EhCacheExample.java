package com.example.ehcache;


import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.concurrent.Executors;

public class EhCacheExample {

    public static void main(String[] args) {
        //创建缓存管理器
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        // 初始化
        cacheManager.init();
        // 创建缓存存储器
        Cache<String, String> mycache = cacheManager.createCache("mycache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, String.class, ResourcePoolsBuilder.heap(10)));

        mycache.put("key", "hello,java");

        String value = mycache.get("key");

        System.out.println(value);

        cacheManager.close();
        Executors
    }
}
