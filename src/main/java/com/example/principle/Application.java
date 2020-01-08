package com.example.principle;


/**
 * 实现配置热更新，通过定时任务
 */
public class Application {
    //zookeeper
    private static ConfigSource configSource = new ZookeeperConfigSource();

    private static final RedisConfig redisConfig = new RedisConfig(configSource);
    private static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);
    private static final MySqlConfig mySqlConfig = new MySqlConfig(configSource);

    public static void main(String[] args) {

        ScheduledUpdater redisConfigUpdater = new ScheduledUpdater(redisConfig, 1, 1);
        redisConfigUpdater.run();
        ScheduledUpdater kafkaConfigUpdater = new ScheduledUpdater(kafkaConfig, 1, 3);
        kafkaConfigUpdater.run();

        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1", 8080);
        simpleHttpServer.addViewers("/config", redisConfig);
        simpleHttpServer.addViewers("/config", mySqlConfig);
        simpleHttpServer.run();


    }
}
