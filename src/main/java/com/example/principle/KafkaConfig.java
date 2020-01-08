package com.example.principle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaConfig implements Updater {


    private ConfigSource configSource;
    private String address;
    private int timeout;
    private int port;

    public KafkaConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }


    @Override
    public void update() {
        //从configSource加载 address/timeout/maxTotle
        log.info("KafkaConfig update");

    }


    public ConfigSource getConfigSource() {
        return configSource;
    }

    public void setConfigSource(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
