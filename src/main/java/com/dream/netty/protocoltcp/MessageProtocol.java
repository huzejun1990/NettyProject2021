package com.dream.netty.protocoltcp;

/**
 * @ClassName : MessageProtocal
 * @Author : huzejun
 * @Date: 2021/5/6-17:48
 */

//协议包
public class MessageProtocol {
    private int len;    //关键

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
