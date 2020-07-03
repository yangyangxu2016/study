package com.example.ehcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * 定时删除
 * 主动删除
 * 定期删除
 * @author xuyy
 */
public class GuavaExample {
    public static void main(String[] args) throws ExecutionException {

        // 创建方式一：LoadingCache
        LoadingCache<String, String> loadCache = CacheBuilder.newBuilder()
                // 并发级别设置为 5，是指可以同时写缓存的线程数
                .concurrencyLevel(5)
                // 设置 8 秒钟过期
                .expireAfterAccess(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为 10
                .initialCapacity(10)
                // 设置缓存最大容量为 100，超过之后就会按照 LRU 算法移除缓存项
                .maximumSize(100)
                // 设置要统计缓存的命中率
                .recordStats()
                // 设置缓存的移除通知
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                //指定 CacheLoader，缓存不存在时，可自动加载缓存
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "cache-value" + key;
                    }
                });


        loadCache.put("c1", "hello java");

        String val = loadCache.get("c1");

        System.out.println(val);

        String xuyy = loadCache.get("xuyy");

        System.out.println(xuyy);

    }
}
