package com.dream.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: huzejun
 * @Date: 2021/2/16 19:11
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
    // F:\githubcode\NettyProject2021\src\01.txt
        FileInputStream fileInputStream = new FileInputStream("1.txt");
//        FileInputStream fileInputStream = new FileInputStream("F:\\githubcode\\NettyProject2021\\src\\01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("3.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){   //循环读取
            // 这是有一个重要的操作，一定不要忘了
            byteBuffer.clear(); //清空buffer
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read = " + read);
            if (read == -1){    //表示读完
                break;
            }
            //将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
            //关闭相关的流
            fileInputStream.close();
            fileOutputStream.close();

    }

}
