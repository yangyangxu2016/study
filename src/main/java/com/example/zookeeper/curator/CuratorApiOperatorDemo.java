package com.example.zookeeper.curator;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.aop.scope.ScopedProxyUtils;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorApiOperatorDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorUtils.getInstance();
        System.out.println("连接成功");


        //创建节点
//        curatorFramework.create().creatingParentsIfNeeded().
//                withMode(CreateMode.PERSISTENT).forPath("/curator/curator1/2", "123".getBytes());

        //删除节点订单nnvv
//        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");

        //获取数据
//        Stat stat = new Stat();
//        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/curator/curator1/2");
//        System.out.println(new String(bytes) + "--->" + stat);

        //异步创建
//        ExecutorService service = Executors.newFixedThreadPool(1);
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
//                inBackground(new BackgroundCallback() {
//                    @Override
//                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
//                        System.out.println(Thread.currentThread().getName() + "-->result" + event.getResultCode() +
//                                "--->" + event.getType());
//                        countDownLatch.countDown();
//                    }
//                }, service)
//                .forPath("mic", "123".getBytes());
//        countDownLatch.await();
//        service.shutdown();


//        事务操作（curator独有的）
        Collection<CuratorTransactionResult> commit = curatorFramework.inTransaction().create().forPath("/curator1", "111".getBytes()).and()
                .setData().forPath("/curator", "333".getBytes()).and().commit();

        for (CuratorTransactionResult result : commit) {
            System.out.println(result.getForPath() + "--->" + result.getType());
        }

    }
}
