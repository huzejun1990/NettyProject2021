package com.dream.netty.protocoltcp2022;

/**
 * @Author : huzejun
 * @Date: 2022/3/5-18:51
 */
// 协议包
public class MessageProtocol {
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
