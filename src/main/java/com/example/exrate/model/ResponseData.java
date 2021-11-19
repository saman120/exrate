package com.example.exrate.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData implements Serializable {
    private String msg;
    private Float value;

    public ResponseData() {
    }

    public ResponseData(float purchaseAmount) {
        value = purchaseAmount;
    }

    public ResponseData(String msg) {
        this.msg = msg;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
