package com.example.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorUtils {
    private final static String connectionString = "140.143.236.197:2181";

    public static CuratorFramework getInstance() {
        //创建回话的两种方式
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(connectionString, 5000,
                        5000, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        return curatorFramework;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
