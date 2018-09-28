package com.example.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionDemo {
    private final static String connectionString = "140.143.236.197:2181";

    private static ZkClient getInstance() {
        return new ZkClient(connectionString, 5000);
    }

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = getInstance();
        zkClient.delete("/xuyy1");

        //创建节点
        zkClient.createPersistent("/xuyy", true);
        zkClient.createPersistent("/xuyy1", "xxx");

        //删除节点
        zkClient.delete("/node12/aaa");
        zkClient.deleteRecursive("/nodes");

        //获取子节点
        List<String> node12332 = zkClient.getChildren("/node12332");
        System.out.println(node12332);


        //watcher 订阅
        zkClient.subscribeDataChanges("/xuyy1", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(s + "-->" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点删除");
            }
        });

        zkClient.writeData("/xuyy1", "1233");
        TimeUnit.SECONDS.sleep(2);

        zkClient.delete("/xuyy1");


    }
}
