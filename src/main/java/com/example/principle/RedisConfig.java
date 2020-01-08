package com.example.principle;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class RedisConfig implements Updater ,Viewer {


    private ConfigSource configSource;
    private String address;
    private int timeout;
    private int port;

    public RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }


    @Override
    public void update() {
        //从configSource加载 address/timeout/maxTotle
        log.info("RedisConfig update");

    }

    @Override
    public String outputPlainText() {
        return null;
    }

    @Override
    public Map<String, String> output() {
        return null;
    }


}
