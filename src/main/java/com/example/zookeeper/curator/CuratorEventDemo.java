package com.example.zookeeper.curator;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.NodeCache;

public class CuratorEventDemo {


    //pathCache 监视一个路径下子节点的创建、删除、节点数据更新
    //NodeCache 监视一个节点的创建、更新、删除
    //TreeCache  两者的合体 （监视路径下的创建、更新、删除事件）


    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorUtils.getInstance();


//        节点变化
        NodeCache nodeCache = new NodeCache(curatorFramework, "/curator", false);
        nodeCache.start(true);

        nodeCache.getListenable().addListener(() -> System.out.println("节点数据发生变化，变化" +
                "后的数据为： " + new String(nodeCache.getCurrentData().getData())));

            curatorFramework.setData().forPath("/curator", "菲菲1".getBytes());
        System.out.println("1");
            System.in.read();
        System.out.println("2");






    }
}
