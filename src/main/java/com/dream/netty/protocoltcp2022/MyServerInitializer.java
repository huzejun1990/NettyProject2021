package com.dream.netty.protocoltcp2022;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author : huzejun
 * @Date: 2022/3/5-16:12
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyMessageEncoder());   //编码器
        pipeline.addLast(new MyMessageDecoder()); //解码器
        pipeline.addLast(new MyServerHandler());
    }


}
