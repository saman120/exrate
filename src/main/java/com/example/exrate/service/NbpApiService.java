package com.example.exrate.service;

import com.example.exrate.model.CachedExchangeRate;
import com.example.exrate.model.ExchangeRateInfo;
import com.example.exrate.util.DataNotFoundException;
import com.example.exrate.util.DtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class NbpApiService implements ExchangeRateApiService {
    private WebClient webClient;

    public NbpApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<CachedExchangeRate> fetchDataOn(LocalDate date) {
        ExchangeRateInfo[] block = webClient.get()
                .uri("/exchangerates/tables/C/" + date + "/")
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    return Mono.error(new DataNotFoundException(
                            String.format("Failed! No Data found on %s", date)
                    ));
                })
                .bodyToMono(ExchangeRateInfo[].class).block();

        List<CachedExchangeRate> cachedExchangeRateList = DtoConverter.convertToEntityList(block[0]);

        return cachedExchangeRateList;
    }
    @Override
    public List<CachedExchangeRate> fetchMidDataOn(LocalDate date) {
        ExchangeRateInfo[] block = webClient.get()
                .uri("/exchangerates/tables/A/" + date + "/")
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    return Mono.error(new DataNotFoundException(
                            String.format("Failed! No Data found on %s", date)
                    ));
                })
                .bodyToMono(ExchangeRateInfo[].class).block();

        List<CachedExchangeRate> cachedExchangeRateList = DtoConverter.convertToEntityList(block[0]);

        return cachedExchangeRateList;
    }
}
