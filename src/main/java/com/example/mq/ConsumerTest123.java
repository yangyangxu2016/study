package com.example.mq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.UUID;

public class ConsumerTest123 {

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("GID_aiot_safeguard");
        consumer.setInstanceName(UUID.randomUUID().toString());
        consumer.setConsumeMessageBatchMaxSize(32);
        consumer.setNamesrvAddr("10.171.16.87:10911");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt me : list) {
                    System.out.print(new String(me.getBody()) + "\n");
                }
                System.out.println("=====================================");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            consumer.subscribe("TP_biz_iot", "*");
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
