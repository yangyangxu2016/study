package com.example.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class AuthControlDemo implements Watcher {
    private final static String connectionString = "140.143.236.197:2181";


    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(connectionString, 5000, new AuthControlDemo());
        countDownLatch.await();
        zooKeeper.addAuthInfo("digest", "root:root".getBytes());

        zooKeeper.create("/auth", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        ZooKeeper zooKeeper1 = new ZooKeeper(connectionString, 5000, new AuthControlDemo());
        countDownLatch.await();
        zooKeeper1.delete("/auth", -1);


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
            countDownLatch2.countDown();

            System.out.println(watchedEvent.getState());

        }
    }
}
