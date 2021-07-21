package com.bhavya.ntccmain.modles;

public class msgModel {

    String uID, msg;
    Long timestamp;

    public msgModel(String uID, String msg, Long timestamp) {
        this.uID = uID;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public msgModel(String uID, String msg) {
        this.uID = uID;
        this.msg = msg;
    }

    public msgModel(){}

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
