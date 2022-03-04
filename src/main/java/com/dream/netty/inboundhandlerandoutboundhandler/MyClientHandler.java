package com.dream.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author : huzejun
 * @Date: 2022/3/4-18:28
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("服务器的ip= " + ctx.channel().remoteAddress());
        System.out.println("收到服务器数据消息= " + msg);
/*        System.out.println("从客户端" + ctx.channel().remoteAddress() + "读取long " +  msg);
        //给客户端发关一个long
        ctx.writeAndFlush(98765L);*/
    }
    //重写 channelActive 发送数据


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
//        ctx.writeAndFlush(Unpooled.copiedBuffer(""))
        ctx.writeAndFlush(123456L);  //发送的是一个Long

        //分析
        //1.“abcdabcdabcdabcd” 是 16个字符
        //2.该处理器的前一个handler 是 MyLongToByteEncoder
        //3 MyLongToByteEncoder 父类 MessageToByteEncoder
        //4. 父类 MessageToByteEncoder
        /*
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = null;
        try {
            if (acceptOutboundMessage(msg)) { //判断当前 msg 是不是应该处理的类型，如果是就处理，不是就跳过encoder
                @SuppressWarnings("unchecked")
                I cast = (I) msg;
                buf = allocateBuffer(ctx, cast, preferDirect);
                try {
                    encode(ctx, cast, buf);
                } finally {
                    ReferenceCountUtil.release(cast);
                }

                if (buf.isReadable()) {
                    ctx.write(buf, promise);
                } else {
                    buf.release();
                    ctx.write(Unpooled.EMPTY_BUFFER, promise);
                }
                buf = null;
            } else {
                ctx.write(msg, promise);
            }
        } catch (EncoderException e) {
            throw e;
        } catch (Throwable e) {
            throw new EncoderException(e);
        } finally {
            if (buf != null) {
                buf.release();
            }
        }
    }
        4. 因此我们编写 Encoder 时要注意传入的数据类型和处理的数据类型一致
         */
        //
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
