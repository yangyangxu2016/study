//package com.example.springcloud.ribbon;
//
//import com.netflix.loadbalancer.*;
//import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
//import com.netflix.loadbalancer.reactive.ServerOperation;
//import rx.Observable;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class RibbonClientDemo {
//
//    public static void main(String[] args) {
//        List<Server> serverList = Arrays.asList(new Server("www.baidu.com", 80), new Server("zhihu.com", 80));
//        BaseLoadBalancer baseLoadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
//        baseLoadBalancer.setRule(new RoundRobinRule());
//        for (int i = 0; i < 5; i++) {
//            LoadBalancerCommand.<String>builder().withLoadBalancer(baseLoadBalancer).build().submit(new ServerOperation<String>() {
//                @Override
//                public Observable<String> call(Server server) {
//                    String addr = "http://" + server.getHost() + ":" + server.getPort();
//                    System.out.println("调用地址：" + addr);
//                    return Observable.just(addr);
//                }
//            }).toBlocking().first();
//
//        }
//
//
//    }
//}
