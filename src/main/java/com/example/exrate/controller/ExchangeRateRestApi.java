package com.example.exrate.controller;

import com.example.exrate.model.ResponseData;
import com.example.exrate.service.ExchangeRateCalcService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ExchangeRateRestApi {
    private ExchangeRateCalcService exchangeRateCalcService;

    public ExchangeRateRestApi(ExchangeRateCalcService exchangeRateCalcService) {
        this.exchangeRateCalcService = exchangeRateCalcService;
    }

    @GetMapping("/sell-exchange-rate")
    ResponseEntity getSellExchangeRate(
            @RequestParam("cur_code") String curCode,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam("date") LocalDate date){
        return ResponseEntity.ok(new ResponseData(exchangeRateCalcService.getSellExchangeRate(curCode, date)));
    }

    @GetMapping("/purchase-cost")
    ResponseEntity getPurchaseAmount(
            @RequestParam("cur_codes") List<String> curCodeArray,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam("date") LocalDate date){

        return ResponseEntity.ok(new ResponseData(exchangeRateCalcService.getPurchaseAmount(curCodeArray, date)));
    }
}
