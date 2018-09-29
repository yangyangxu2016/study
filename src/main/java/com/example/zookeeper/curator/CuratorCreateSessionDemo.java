package com.example.zookeeper.curator;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorCreateSessionDemo {

    private final static String connectionString = "140.143.236.197:2181";

    public static void main(String[] args) {
        //创建回话的两种方式
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(connectionString, 5000,
                        5000, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();

//        fluent风格
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder().
                connectString(connectionString).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).
                namespace("/curator").build();

        curatorFramework1.start();

        System.out.println("success");



    }
}
