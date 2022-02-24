package com.dream.nio;

/**
 * @ClassName : MappedByteBuffer
 * @Author : huzejun
 * @Date: 2021/5/3-20:23
 */

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明
 * 1、MappedByteBuffer 可让文件直接在内存(堆外内存)修改，操作系统不需要拷贝一次
 * 2、
 * 3、
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        //获取对应的文件通道
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 参数1：FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2：0 ：可以直接修改的起始位置
         * 参数3：5： 是映射到内存的大小（不是索引位置），即 将文件 1.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0,(byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        mappedByteBuffer.put(4, (byte) 'Y');

        randomAccessFile.close();
        System.out.println("修改成功！");
    }
}
