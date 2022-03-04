package com.dream.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author : huzejun
 * @Date: 2022/3/4-18:08
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //加入一个出栈的 handler 对数据进行一个编码
        pipeline.addLast(new MyLongToByteEncoder());

        //这是一个入栈的解码器（入栈handler）
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongDecoder2());
        // 加入一个自定义的 handler,处理业务
        pipeline.addLast(new MyClientHandler());

    }
}
