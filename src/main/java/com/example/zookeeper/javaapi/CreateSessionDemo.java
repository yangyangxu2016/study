package com.example.zookeeper.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateSessionDemo {

    private final static String connectionString = "140.143.236.197:2181";


    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(connectionString, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                    System.out.println(watchedEvent.getState());

                }
            }
        });

        countDownLatch.await();

        System.out.println(zooKeeper.getState());


    }

}



