package com.example.exrate.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

public class CachedExchangeRatePk implements Serializable {
    private LocalDate effectiveDate;
    private String code;

    public CachedExchangeRatePk() {
    }

    public CachedExchangeRatePk(LocalDate effectiveDate, String code) {
        this.effectiveDate = effectiveDate;
        this.code = code;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
