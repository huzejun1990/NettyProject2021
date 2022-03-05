package com.dream.netty.protocoltcp2022;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author : huzejun
 * @Date: 2022/3/4-18:05
 */
public class MyClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer()); //自定义一个初始化类


            ChannelFuture channelFuture = bootstrap.connect("localhost", 7000).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            group.shutdownGracefully();

        }
    }

/*public static void main(String[] args)  throws  Exception{

    EventLoopGroup group = new NioEventLoopGroup();

    try {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new MyClientInitializer()); //自定义一个初始化类

        ChannelFuture channelFuture = bootstrap.connect("localhost", 7000).sync();

        channelFuture.channel().closeFuture().sync();

    }finally {
        group.shutdownGracefully();
    }
}*/
}
