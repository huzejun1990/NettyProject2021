package com.dream.netty.dubborpc.publicinterface;

/**
 * @ClassName : HelloService
 * @Author : huzejun
 * @Date: 2021/5/6-23:19
 */

//这个是接口，是服务提供方 和 服务消费方都需要
public interface HelloService {

    String hello(String msg);
}
