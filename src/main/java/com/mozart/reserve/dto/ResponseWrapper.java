package com.mozart.reserve.dto;



public class ResponseWrapper {
    private int resCode;
    private String resMessage;

    public ResponseWrapper(int resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }
}
