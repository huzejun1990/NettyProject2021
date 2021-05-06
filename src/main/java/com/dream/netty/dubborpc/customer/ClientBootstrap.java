package com.dream.netty.dubborpc.customer;

import com.dream.netty.dubborpc.netty.NettyClient;
import com.dream.netty.dubborpc.publicinterface.HelloService;

/**
 * @ClassName : ClientBootstrap
 * @Author : huzejun
 * @Date: 2021/5/7-1:16
 */
public class ClientBootstrap {
    //这里定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws Exception {

        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (;; ) {
        Thread.sleep(2 * 1000);
        //通过代表对象调用服务提供者的方法(服务)
        String res = service.hello("你好 dubbo~");
        System.out.println("调用的结果 res= " + res);
        }
    }

}
