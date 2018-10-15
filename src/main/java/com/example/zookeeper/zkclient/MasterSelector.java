package com.example.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MasterSelector {
    private ZkClient zkClient;
    private final static String MASTER_PATH = "/master";//需要争抢的节点
    private IZkDataListener dataListener;//注册京东内容变化
    private UserCenter server;//其他服务器
    private UserCenter master;//master节点
    public static boolean isRunning = false;

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public MasterSelector(ZkClient zkClient, UserCenter server) {
        this.zkClient = zkClient;
        this.server = server;
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //如果节点被删除发出选主操作
                chooseMaster();
                System.out.println("触发节点删除事件！"+s.getBytes());
            }

        };
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            zkClient.subscribeDataChanges(MASTER_PATH, dataListener);//注册节点事件
            chooseMaster();
        }
    }

    private void chooseMaster() {
        if (!isRunning) {
            System.out.println("当前服务没有启动！");
            return;
        }
        try {
            zkClient.createEphemeral(MASTER_PATH, server);
            master = server;

            System.out.println("客户端： " + master.getMc_id() + " 我现在已经是master了，你们现在要听我的了");
            //定时器
            //释放锁 每5s释放一次
            scheduledExecutorService.schedule(() -> releaseMaster(), 5, TimeUnit.SECONDS);

        } catch (ZkNodeExistsException e) {
            //表示master已经存在
            UserCenter userCenter = zkClient.readData(MASTER_PATH, true);
            if (userCenter == null) {
                chooseMaster();
            } else {
                master = userCenter;
            }
        }
    }

    private void releaseMaster() {
        if (checkIsMaster()) {
            System.out.println("释放master");
            zkClient.delete(MASTER_PATH);
        }
    }

    private boolean checkIsMaster() {
        UserCenter userCenter = zkClient.readData(MASTER_PATH);
        if (userCenter.getMc_name().equals(server.getMc_name())) {
            master = userCenter;
            return true;
        }
        return false;
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            scheduledExecutorService.shutdown();
            zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
            releaseMaster();
        }
    }
}
