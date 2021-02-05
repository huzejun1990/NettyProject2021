package com.dream.nio;

import java.nio.IntBuffer;

/**
 * @Author: huzejun
 * @Date: 2021/2/5 20:52
 */
public class BasicBuffer {
    public static void main(String[] args) {

        //举例说明Buffer的使用
        //创建一个Buffer,大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer，存放数据
//        intBuffer.put(10);
//        intBuffer.put(10);
//        intBuffer.put(10);
//        intBuffer.put(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put( i * 2);
        }

        //如何从buffer读取数据
        //将buffer转换，读写切换
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }

}
