package com.example.closesecret.net;

public class Message {

    public Message(String msgId,String msg,String phone_md5,String msgtime) {
        this.msg = msg;
        this.msgId = msgId;
        this.phone_md5 = phone_md5;
        this.msgtime =msgtime;
    }

    private String msgId=null,msg=null,phone_md5=null,msgtime=null;

    public String getMsg() {
        return msg;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getPhone_md5() {
        return phone_md5;
    }

    public String getMsg_time() {
        return msgtime;
    }
}

