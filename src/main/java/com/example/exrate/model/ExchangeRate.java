package com.example.exrate.model;

public class ExchangeRate {
    private String currency;
    private String code;
    private Float bid;
    private Float mid;
    private Float ask;

    public Float getMid() {
        return mid;
    }

    public void setMid(Float mid) {
        this.mid = mid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }
}
