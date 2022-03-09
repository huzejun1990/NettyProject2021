package com.dream.netty.heartbeat;

/**
 * @Author : huzejun
 * @Date: 2022/3/7-23:33
 */
public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(System.nanoTime());  //纳秒  10亿分之一
        Thread.sleep(1000);
        System.out.println(System.nanoTime());
    }
}
