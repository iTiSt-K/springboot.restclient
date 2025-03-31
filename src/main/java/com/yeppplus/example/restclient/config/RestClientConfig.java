package com.yeppplus.example.restclient.config;

import java.time.Duration;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.boot.http.client.SimpleClientHttpRequestFactoryBuilder;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestClientConfig {
    // private static final Logger log = LoggerFactory.getLogger(RestClientConfig.class);

    /**
     * 공통 RestClient 설정 - 요청 및 응답 로깅 포함
     */
    @Bean
    public RestClientCustomizer loggingCustomizer() {
        return restClientBuilder -> restClientBuilder
            // .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactoryBuilder())) // 요청, 응답 바디 로깅 가능
            .requestInterceptor((request, body, execution) -> {
                log.info("Request: {} {}", request.getMethod(), request.getURI());
                log.info("Headers: {}", request.getHeaders());
                if (body.length > 0) {
                    log.info("Request Body: {}", new String(body));
                }
                var response = execution.execute(request, body);
                log.info("Response Status: {}", response.getStatusCode());
                log.info("Response Headers: {}", response.getHeaders());
                return response;
            });
    }

    /**
     * A API 전용 RestClient
     */
    @Bean
    public RestClient aApiRestClient(RestClient.Builder builder) {
        return builder
            .baseUrl("https://openapi.naver.com") // A API 서버 주소
            .defaultHeaders(headers -> {
                headers.set("Content-Type", "application/json");
                headers.set("X-Naver-Client-Id", "clZLm4qSERuqQ0GExDL2");
                headers.set("X-Naver-Client-Secret", "PBH36dqokq");})
            .build();
    }

    /**
     * B API 전용 RestClient
     */
    @Bean
    public RestClient bApiRestClient(RestClient.Builder builder) {
        return builder
            .baseUrl("https://dapi.kakao.com") // B API 서버 주소
            .defaultHeaders(headers -> {
                headers.set("Content-Type", "application/json");
                headers.set("authorization", "KakaoAK a6ef83a905c7ffe6ef3c44bec8d133ad");})
            .build();
    }  
}
