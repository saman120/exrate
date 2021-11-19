package com.example.exrate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
@IdClass(CachedExchangeRatePk.class)
public class CachedExchangeRate implements Serializable {
    @Id
    private LocalDate effectiveDate;
    @Id
    @Column(name = "cur_code")
    private String code;
    @Column(name = "cur_table")
    private String table;
    @Column(name = "cur_no")
    private String no;
    private LocalDate tradingDate;
    private String currency;
    @Column(name = "cur_bid")
    private Float bid;
    @Column(name = "cur_ask")
    private Float ask;
    @Column(name = "cur_mid")
    private Float mid;

    public Float getMid() {
        return mid;
    }

    public void setMid(Float mid) {
        this.mid = mid;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CachedExchangeRate that = (CachedExchangeRate) o;
        return Objects.equals(effectiveDate, that.effectiveDate) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effectiveDate, code);
    }
}
