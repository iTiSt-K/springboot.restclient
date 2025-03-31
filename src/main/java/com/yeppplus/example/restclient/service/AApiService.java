package com.yeppplus.example.restclient.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AApiService {
    private final RestClient aApiRestClient;

    public AApiService(@Qualifier("aApiRestClient") RestClient aApiRestClient) {
        this.aApiRestClient = aApiRestClient;
    }

    public String getAData(String query) {
        // A API에 대한 요청을 처리하는 메서드
        return aApiRestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v1/search/webkr.json")  // A API의 엔드포인트
                .queryParam("query", query) // 쿼리 파라미터 추가
                .queryParam("display", 10)      // 추가 파라미터
                .build())
            .retrieve()
            .body(String.class);
    }
}
