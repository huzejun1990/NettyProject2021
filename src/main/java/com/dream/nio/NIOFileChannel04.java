package com.dream.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @Author: huzejun
 * @Date: 2021/2/16 19:55
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {

        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("E:\\File\\img\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\File\\img\\a2.jpg");

        //获取各个流对应的filechannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        // 使用 transferForm完成拷贝
        destCh.transferFrom(sourceCh,0,sourceCh.size());
        //关闭相关的通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
