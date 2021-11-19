package com.example.exrate;

import com.example.exrate.model.ExchangeRateInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootTest
class ExrateApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void webclientTest() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://api.nbp.pl/api")
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
				.build();

		ExchangeRateInfo[] block = webClient.get()
				.uri("/exchangerates/tables/C/2021-11-09/")
				.retrieve()
				.bodyToMono(ExchangeRateInfo[].class).block();

		System.out.println(block);
	}

}
