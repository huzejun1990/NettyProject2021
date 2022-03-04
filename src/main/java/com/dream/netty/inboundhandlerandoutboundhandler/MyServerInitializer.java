package com.dream.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author : huzejun
 * @Date: 2022/3/4-16:48
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();   //一会儿下断点

        //入栈的 handler进行解码 MyByteToLongDecoder
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongDecoder2());

        //出栈的handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //自定义handler处理业务逻辑
        pipeline.addLast(new MyServerHandler());
    }
}
