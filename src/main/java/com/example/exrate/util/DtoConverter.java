package com.example.exrate.util;

import com.example.exrate.model.CachedExchangeRate;
import com.example.exrate.model.ExchangeRateInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {
    public static List<CachedExchangeRate> convertToEntityList(ExchangeRateInfo block){
        return block.getRates().stream().map(exchangeRate -> {
            CachedExchangeRate cachedExchangeRate = new CachedExchangeRate();
            BeanUtils.copyProperties(block, cachedExchangeRate);
            BeanUtils.copyProperties(exchangeRate, cachedExchangeRate);
            return cachedExchangeRate;
        }).collect(Collectors.toList());
    }

}
