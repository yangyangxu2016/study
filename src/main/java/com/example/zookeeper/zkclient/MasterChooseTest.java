package com.example.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MasterChooseTest {
    private final static String connectionString = "140.143.236.197:2181";

    public static void main(String[] args) throws InterruptedException {
        List<ZkClient> clients = new ArrayList<>();
        List<MasterSelector> masterSelectors = new ArrayList<>();
        try {

            for (int i = 0; i < 10; i++) {
                ZkClient zkClient = new ZkClient(connectionString, 5000, 5000,
                        new SerializableSerializer());
                clients.add(zkClient);

                UserCenter userCenter = new UserCenter();
                userCenter.setMc_id(i);
                userCenter.setMc_name("我是客户端：" + i);

                MasterSelector masterSelector = new MasterSelector(zkClient, userCenter);
                masterSelectors.add(masterSelector);
                masterSelector.start();

                TimeUnit.SECONDS.sleep(4);

            }
        } finally {
            for (MasterSelector masterSelector : masterSelectors) {
                masterSelector.stop();
            }

        }


    }

}
