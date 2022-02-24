package com.dream.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @Author : huzejun
 * @Date: 2022/2/22-18:49
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        // 向管道加入 处理器


        //得到管道
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个netty 提供的 httpServerCodec codec =>[ocder - decoder
        // HttpServerCodec 说明
        //1.HttpServerCodec 是netty 提供的处理http的 编-解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        //2.增加一个自定义的处理器（handler）
        pipeline.addLast("MyTestHttpServerHandler",new TestHttpServerHandler());

        System.out.println("ok~~~~~~~~~~~");
    }
}
