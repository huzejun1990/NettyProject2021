package com.dream.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author : huzejun
 * @Date: 2022/3/1-19:09
 */
public class MyServer {
    public static void main(String[] args) throws Exception {

        //创建两个线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();   //8个NioEventLoop
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))  //在bossGroup 增加一个 日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            //因为基于http协议，使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式天之翼，添加 ChunkedWriteHandler 处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                            说明
                            1.http数据在传输过程中是分段,HttpObjectAggregator:就是可以将多个段聚合
                            2.这就是为什么，当浏览器发送大量数据时，就会发出多次http请求的原因
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                             说明：
                             1.对于webSocker,它的数据是以帧（frame）形式传递
                             2.可以看到WebSocketFrame 下面有6个子类
                             3.浏览器请求时 ws://localhost:7000/xxx 表示请求的uri
                             4.WebSocketServerProtocolHandler 核心功能是将http协议升级为 ws 协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello2"));

                            //自定义的 handler,处理业务 逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());

                        }
                    });

            //启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
