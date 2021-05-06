package com.dream.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName : MyServerInitializer
 * @Author : huzejun
 * @Date: 2021/5/6-16:13
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyMessageDecoder());  //解码器
        pipeline.addLast(new MyMessageEncoder());   //编码器
        pipeline.addLast(new MyServerHandler());

    }
}
