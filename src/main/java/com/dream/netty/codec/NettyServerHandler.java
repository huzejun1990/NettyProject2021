package com.dream.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
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
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {

    public void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student msg) throws Exception {

        //读取从客户端发送的StudentPOJO.Student
        System.out.println("客户端发送的数据 id=" + msg.getId() + "名字 =" + msg.getName());
    }
    //读取数据实际（这里我们可以读取客户端发送的消息）

/*
    */
/**
     * 1、ChannelHandlerContext ctx:上下文对象，含有 pipeline,通道channel,地址
     * 2、Object msg：就是客户端发送的数据 默认Object
     * 3、
     * @param ctx
     * @param msg
     * @throws Exception
     *//*

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //读取从客户端发送的StudentPOJO.Student
        StudentPOJO.Student student = (StudentPOJO.Student) msg;

        System.out.println("客户端发送的数据 id=" + student.getId() + "名字 =" + student.getName());
    }
*/

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
