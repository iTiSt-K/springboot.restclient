package com.yeppplus.example.restclient.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BApiService {
    private final RestClient bApiRestClient;

    public BApiService(@Qualifier("bApiRestClient") RestClient bApiRestClient) {
        this.bApiRestClient = bApiRestClient;
    }

    public String getBData(String query) {
        // B API에 대한 요청을 처리하는 메서드
        return bApiRestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v2/search/web")  // B API의 엔드포인트
                .queryParam("query", query) // 쿼리 파라미터 추가
                .queryParam("size", 10)      // 추가 파라미터
                .build())
            .retrieve()
            .body(String.class);
    }
}
