package com.dream.nio;

import java.nio.ByteBuffer;

/**
 * @ClassName : NIOByteBufferPutGet
 * @Author : huzejun
 * @Date: 2021/5/3-20:09
 */
public class NIOByteBufferPutGet {

    public static void main(String[] args) {

        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('天');
        buffer.putShort((short) 4);

        //取出
        buffer.flip();

        System.out.println();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
