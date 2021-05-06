package com.dream.netty.dubborpc.provider;

import com.dream.netty.dubborpc.netty.NettyServer;

/**
 * @ClassName : ServerBootstrap
 * @Author : huzejun
 * @Date: 2021/5/6-23:32
 */

//ServerBootstrap 会启动一个服务提供者，就是NettyServer
public class ServerBootstrap {

    public static void main(String[] args) {

        //代码
        NettyServer.startServer("127.0.0.1",7000);

    }
}
