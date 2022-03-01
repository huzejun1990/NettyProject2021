package com.dream.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author : huzejun
 * @Date: 2022/2/24-23:36
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {

        // 创建一个ByteBuf
        //说明
        //1.创建 对象，该对象包含一个数组arr,是一个byte[10]
        //2.在 netty 的 buffer中，不需要使用 flip 进行反转
        // 底层维护了 readerindex 和 writeIndex
        // 3.通过 readderindex 和 writerIndex 和 capacity,将buffer分成三个区域
        // 0 -- readerindex 已经读取的区域
        // readerindex -- writerIndex,可读的区域
        // writeIndex -- capacity,可写的区域
        ByteBuf bufffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            bufffer.writeByte(i);
        }

        System.out.println("capacity=" + bufffer.capacity());  //10
        //输出 
/*        for (int i = 0; i < bufffer.capacity(); i++) {
            System.out.println(bufffer.getByte(i));
        }*/

        for (int i = 0; i < bufffer.capacity(); i++) {
            System.out.println(bufffer.readByte());
        }
        System.out.println("执行完毕");
    }
}
