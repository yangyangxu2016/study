package com.example.principle;

import java.util.Map;

public class MySqlConfig implements Viewer {
    private ConfigSource configSource;
    private String address;
    private int timeout;
    private int port;

    public MySqlConfig(ConfigSource configSource) {
        this.configSource = configSource;
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

    @Override
    public String outputPlainText() {
        return null;
    }

    @Override
    public Map<String, String> output() {
        return null;
    }
}
