package com.dream.netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName : NettyServerHandler
 * @Author : huzejun
 * @Date: 2021/5/5-18:58
 */

/**
 * 说明
 * 1、我们自定义一个 Handler 需要继承netty 规定好的某个 HandlerAdapter
 * 2、这时我们自定义一个Handler,才能称为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际（这里我们可以读取客户端发送的消息）

    /**
     * 1、ChannelHandlerContext ctx:上下文对象，含有 pipeline,通道channel,地址
     * 2、Object msg：就是客户端发送的数据 默认Object
     * 3、
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // writeAndFlush 是 write + flush
        //将数据写入不对劲缓存，并刷新
        //一般讲，我们对这个发送的数据进行强词夺理
//        Thread.sleep(10 * 1000);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~ 喵 2",
//                CharsetUtil.UTF_8));

        //解决方案1 用户程序自定义的普通任务

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {


                Thread.sleep(10 * 1000);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~ 喵 2",
                CharsetUtil.UTF_8));
                } catch (Exception ex){
                    System.out.println("发生异常 " + ex.getMessage());
                }
            }
        });
        ///
        //解决方案1 用户程序自定义的普通任务

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~ 喵 3",
                            CharsetUtil.UTF_8));
                } catch (Exception ex){
                    System.out.println("发生异常 " + ex.getMessage());
                }
            }
        });



        System.out.println("go on .....");

/*        System.out.println("服务器读取线程：" + Thread.currentThread().getName());
        System.out.println("server ctx = "+ctx);
        System.out.println("看看channel 和 pipeline的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();  //本质是一个双向链表
        //将 msg 转成一个 ByteBuf
        //ByteBuf 是 Netty提供的，不是 NIO的 ByteBuffer
        ByteBuf  buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址： "+ channel.remoteAddress());*/
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~ 喵 1！", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
