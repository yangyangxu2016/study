package com.example.network.nio.netty.protocal.privates;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息头定义，
 */
@Data
public class Header {

    private int crcCode = 0xabef0101;
    private int length;//消息长度，包括消息头和消息体
    private long sessionID;//会话 集群节点内全局唯一，由会话ID生成
    private byte type;//消息类型；
    private byte priority;//消息优先级：0-255
    private Map<String, Object> attachment = new HashMap<String, Object>();//附件



}
