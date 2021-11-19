package com.example.exrate.service;

import com.example.exrate.model.CachedExchangeRate;
import com.example.exrate.model.CachedExchangeRatePk;
import com.example.exrate.repository.CachedErRepository;
import com.example.exrate.util.DataNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExchangeRateCalcServiceImpl implements ExchangeRateCalcService {
    private CachedErRepository cachedErRepository;
    private ExchangeRateApiService exchangeRateApiService;

    public ExchangeRateCalcServiceImpl(
            CachedErRepository cachedErRepository, ExchangeRateApiService exchangeRateApiService) {
        this.cachedErRepository = cachedErRepository;
        this.exchangeRateApiService = exchangeRateApiService;
    }

    private Optional<CachedExchangeRate> getApiDataBy(String curCode, LocalDate date) {
        List<CachedExchangeRate> cachedExchangeRateList = exchangeRateApiService.fetchDataOn(date);

        List<CachedExchangeRate> list = cachedErRepository.findByEffectiveDate(date);
        if (list.isEmpty())
            cachedErRepository.saveAll(cachedExchangeRateList);
        else {
            cachedExchangeRateList.forEach(cachedExchangeRate -> {
                Optional<CachedExchangeRate> oldCacheData = list.stream()
                        .filter(cachedExchangeRate1 -> cachedExchangeRate1.equals(cachedExchangeRate))
                        .findFirst();
                if(oldCacheData.isPresent()){
                    oldCacheData.get().setAsk(cachedExchangeRate.getAsk());
                    oldCacheData.get().setBid(cachedExchangeRate.getBid());
                    cachedErRepository.save(oldCacheData.get());
                }else {
                    cachedErRepository.save(cachedExchangeRate);
                }
            });
        }

        return cachedExchangeRateList.stream()
                .filter(cachedExchangeRate -> cachedExchangeRate.getCode().equalsIgnoreCase(curCode))
                .findFirst();
    }

    private List<CachedExchangeRate> getMidApiDataBy(List<String> curCodes, LocalDate date) {
        List<CachedExchangeRate> cachedExchangeRateList = exchangeRateApiService.fetchMidDataOn(date);

        List<CachedExchangeRate> list = cachedErRepository.findByEffectiveDate(date);
        if (list.isEmpty())
            cachedErRepository.saveAll(cachedExchangeRateList);
        else {
            cachedExchangeRateList.forEach(cachedExchangeRate -> {
                Optional<CachedExchangeRate> oldCacheData = list.stream().filter(cachedExchangeRate1 -> cachedExchangeRate1.equals(cachedExchangeRate))
                        .findFirst();
                if(oldCacheData.isPresent()){
                    oldCacheData.get().setMid(cachedExchangeRate.getMid());
                    cachedErRepository.save(oldCacheData.get());
                }else {
                    cachedErRepository.save(cachedExchangeRate);
                }
            });
        }

        return cachedExchangeRateList.stream()
                .filter(cachedExchangeRate -> curCodes.contains(cachedExchangeRate.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public Float getSellExchangeRate(String curCode, LocalDate date) {
        Optional<Float> exchangeRate;

        if (cachedErRepository.hasDataOfAsk(date, PageRequest.of(0, 1)).isEmpty()) {
            exchangeRate = getApiDataBy(curCode, date)
                    .map(CachedExchangeRate::getAsk);
        } else {
            exchangeRate = cachedErRepository.findById(new CachedExchangeRatePk(date, curCode))
                    .map(CachedExchangeRate::getAsk);
        }

        return exchangeRate.orElseThrow(() -> new DataNotFoundException(
                String.format("Failed! No Data Found for %s on %s", curCode, date)));
    }

    @Override
    public float getPurchaseAmount(List<String> curCodeList, LocalDate date) {
        List<CachedExchangeRate> cachedExchangeRateList;

        if (cachedErRepository.hasDataOfMid(date, PageRequest.of(0, 1)).isEmpty()) {
            cachedExchangeRateList = getMidApiDataBy(curCodeList, date);
        } else {
            cachedExchangeRateList = cachedErRepository.getDataBy(curCodeList, date);
        }

        return (float) cachedExchangeRateList.stream()
                .mapToDouble(cachedExchangeRate -> Optional.ofNullable(cachedExchangeRate.getMid()).orElse(0.0f))
                .sum();

    }
}
