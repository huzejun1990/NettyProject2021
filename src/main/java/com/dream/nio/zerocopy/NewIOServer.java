package com.dream.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName : NewIOServer
 * @Author : huzejun
 * @Date: 2021/5/4-23:11
 */

//服务器
public class NewIOServer {

    public static void main(String[] args) throws Exception {

        InetSocketAddress address = new InetSocketAddress(7001);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(address);

        //创建一个buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();

            int readCount = 0;
            while (-1 != readCount){
                try {
                    readCount = socketChannel.read(byteBuffer);

                }catch (Exception ex) {
//                    ex.printStackTrace();
                    break;
                }
                //
                byteBuffer.rewind();    //倒带 position = 0 mark 作废

            }

        }

    }
}
