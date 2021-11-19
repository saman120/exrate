package com.example.exrate.service;

import com.example.exrate.model.CachedExchangeRate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateApiService {
    List<CachedExchangeRate> fetchDataOn(LocalDate date);

    List<CachedExchangeRate> fetchMidDataOn(LocalDate date);
}
