package com.example.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ApiOperatorDemo implements Watcher {

    private final static String connectionString = "140.143.236.197:2181";


    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(connectionString, 5000, new ApiOperatorDemo());

        countDownLatch.await();

        System.out.println(zooKeeper.getState());


        String result = zooKeeper.create("/node1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getData("/node1", new ApiOperatorDemo(), stat);
        System.out.println("创建成功" + result);
        TimeUnit.SECONDS.sleep(1);

        //修改数据
        zooKeeper.setData("/node1", "1234".getBytes(), -1);
        TimeUnit.SECONDS.sleep(1);



//  创建节点和子节点
        String path = "/test11";
        Stat stat = zooKeeper.exists(path, true);
        if (stat == null) {
            zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            TimeUnit.SECONDS.sleep(1);
            Stat stat1 = zooKeeper.exists(path + "/node1", true);
            if (stat1 == null) {
                zooKeeper.create(path + "/node1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                TimeUnit.SECONDS.sleep(1);
            }

//修改子路径
            zooKeeper.setData(path + "/node1", "234".getBytes(), -1);
            TimeUnit.SECONDS.sleep(1);

        } else {

            //获取指定节点下的子节点
            List<String> children = zooKeeper.getChildren("/test11", true);
            System.out.println(children);
        }





    }


    @Override
    public void process(WatchedEvent watchedEvent) {
//                如果当前的连接转态是连接成功的，那么通过计数器去控制
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {

            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
                System.out.println(watchedEvent.getState() + "--------" + watchedEvent.getType());
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("数据变更时触发：" + watchedEvent.getPath() + "改变后的值：" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {//子节点的数据变化会触发
                try {
                    System.out.println("子节点数据变更路径：" + watchedEvent.getPath() + " 改变后的值：" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                try {
                    System.out.println("子节点数据变更触发：" + watchedEvent.getPath() + " 节点的值：" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {//子节点删除时会触发
                try {
                    System.out.println("删除路径：" + watchedEvent.getPath() + " 的值：" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
