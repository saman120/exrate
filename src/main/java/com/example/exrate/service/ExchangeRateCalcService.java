package com.example.exrate.service;

import com.example.exrate.model.CachedExchangeRate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateCalcService {
    Float getSellExchangeRate(String curCode, LocalDate date);

    float getPurchaseAmount(List<String> curCodeList, LocalDate date);
}
