package com.example.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class MasterSelector {
    private final static String connectionString = "140.143.236.197:2181";
    private final static String MASTER_PATH = "/curator_master_path";

    public static void main(String[] args) {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString(connectionString).retryPolicy(
                        new ExponentialBackoffRetry(1000, 3))
                .build();

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, MASTER_PATH,
                new LeaderSelectorListenerAdapter() {

                    @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("获取leader成功");
                    TimeUnit.SECONDS.sleep(2);

            }
        });

        leaderSelector.autoRequeue();
        leaderSelector.start();//开始选举
    }
}
