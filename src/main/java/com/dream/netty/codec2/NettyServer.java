package com.dream.netty.codec2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @ClassName : NettyServer
 * @Author : huzejun
 * @Date: 2021/5/5-18:19
 */
public class NettyServer {

    public static void main(String[] args) throws Exception {

        //创建BossGroup 和 WorkerGroup
        //说明
        //1.创建两个线程组 bossGroup 和 workerGroup
        //2.BossGroup 只是处理连接请求，真正的与客户端业务处理会交给 workerGroup完成
        //3.两个都是无限循环
        //4.bossGroup 和 workerGroup 含有的子线程（NioEventLoop）个数
        // 默认实现 cpu核数 * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
        //创建服务器端启动的对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        //使用链式编程来进行设置
        bootstrap.group(bossGroup,workerGroup)  //设置两个线程组
                .channel(NioServerSocketChannel.class)    //NioServerSocketChannel 作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)  //设置保持活动连接状态
//                .handler(null)  //该 handler 对应bossGroup生效，childHandler 对应 childHandler
                .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象(匿名对象)
                    //给pipeline 设置处理器
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ChannelPipeline pipeline = ch.pipeline();
                        //在 pipleline加入ProtoBufDecoder
                        //指定对 那种对象进行解树
                        pipeline.addLast("decoder",new ProtobufDecoder
                                (MyDataInfo.MyMessage.getDefaultInstance()));
                        pipeline.addLast(new NettyServerHandler());
                    }
                });     //给我们的workerGroup 的 EventLoopGroup 对应的管道设置处理器
        System.out.println(".....服务器 is ready...");

        //绑定一个端口并且同步，生成了一个 ChannelFuture 对象
        //启动服务器(并绑定端口)
        ChannelFuture channelFuture = bootstrap.bind(6668).sync();

        // 给cf 注册监听器，监控我们关心的事件
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("监听端口 6668 成功");
                    } else {
                        System.out.println("监听端口 6668 失败");
                    }
                }
            });
        //对关闭通道进行监听
        channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}












