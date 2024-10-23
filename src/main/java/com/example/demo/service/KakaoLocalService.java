package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class KakaoLocalService {

    @Value("${KAKAO_CLIENT_ID}")
    private String KAKAO_CLIENT_ID;

    private final RestTemplate restTemplate;

    @Autowired
    public KakaoLocalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchByKeyword(String query) {
        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + encodeQuery(query);

        String apiKey = "KakaoAK " + KAKAO_CLIENT_ID;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey); // 헤더에 Authorization 추가

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // 오류 로그 출력
            System.err.println("API 호출 오류: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            return null; // 적절한 오류 처리를 원하실 경우 다른 값을 반환하도록 설정
        }
    }

    private String encodeQuery(String query) {
        try {
            return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return query; // 인코딩 실패 시 기본 값 반환
        }
    }
}
