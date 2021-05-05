package com.dream.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName : NIOServer
 * @Author : huzejun
 * @Date: 2021/5/4-13:50
 */

public class NIOServer {

    public static void main(String[] args) throws Exception {

        //创建ServerSocketChannel    -> ServerSocket

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个Selector对象
        Selector selector = Selector.open();

        //编写一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //把 serverSocketChannel 注册到 selector 关心 事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后的 selectionKey 数量="+selector.keys().size()); // 1

        //循环等待客户端连接
        while (true) {

            //这里我们等待1秒钟，如果没有事件发生，返回
            if (selector.select(1000) == 0){    //没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            //如果返回的 > 0,就获取到相关的 SelectionKey 集合
            //1.如果返回的 > 0,表示已经获取关注的事件
            //2. selector.selectedKeys() 返回关注事件的集合
            // 通过 selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys 数量= " + selectionKeys.size());

            //遍历 Set<SelectionKey>，使用迭代器
            Iterator<SelectionKey> KeyIterator = selectionKeys.iterator();

            while (KeyIterator.hasNext()){
                //获取到SelectKey
                SelectionKey key = KeyIterator.next();
                //根据key 对应的通道发生的事件作相应的处理
                if (key.isAcceptable()){    //如果是 OP_ACCEPT,有新的客户端连接我
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个 socketChannel " + socketChannel.hashCode());
                    //将SocketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel 注册到selector,关注事件为 OP_READ,同时给socketChannel
                    //关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端连接后,注册的SelectionKey 数量=" + selector.keys().size());    //2,3,4

                }
                if (key.isReadable()){  //发生OP_Read
                    //通过 key 反向获取对应channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端 " + new String(buffer.array()));

                }

                //手动从集合中，移除当前的selectionKey,防止重复操作
                KeyIterator.remove();

            }


        }
    }


}
