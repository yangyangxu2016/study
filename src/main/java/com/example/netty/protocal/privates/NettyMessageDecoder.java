package com.example.netty.protocal.privates;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    private NettyMarshallingDecoder marshallingDecoder;


    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                               int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                lengthAdjustment, initialBytesToStrip);
        marshallingDecoder = MarshallingCodeCFactory.buildMarshallingDecoder();
    }


    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

//        解码：把字节转换为对象
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
//      读取附件
        int size = frame.readInt();
        if (size > 0) {
            Map<String, Object> attach = new HashMap<>();
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attach.put(key, marshallingDecoder.decode(ctx, frame));
            }
            key = null;
            keyArray = null;
            header.setAttachment(attach);
        }
//        读取消息体
        if (frame.readableBytes() > 0) {
            message.setBody(marshallingDecoder.decode(ctx, frame));
        }
        message.setHeader(header);
        return message;
    }


}
