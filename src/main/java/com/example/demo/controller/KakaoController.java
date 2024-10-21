package com.example.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class KakaoController {

    @Value("${KAKAO_CLIENT_ID}")
    private String KAKAO_CLIENT_ID;

    @Value("${KAKAO_SERVER_REDIRECT_URI}")
    private String KAKAO_SERVER_REDIRECT_URI;

    @GetMapping("/login/kakao")
    public void kakaoLoginRedirect(HttpServletResponse response) throws IOException {
        String clientId = KAKAO_CLIENT_ID; // 카카오 API 클라이언트 ID
        String redirectUri = KAKAO_SERVER_REDIRECT_URI; // 설정한 리디렉션 URI

        String url = "https://kauth.kakao.com/oauth/authorize?client_id=" + clientId
                + "&redirect_uri=" + redirectUri + "&response_type=code";

        response.sendRedirect(url); // 카카오로 리다이렉트
    }

    @GetMapping("/login/kakao/callback")
    public String kakaoCallback(@RequestParam("code") String code) throws JsonProcessingException {
        String clientId = KAKAO_CLIENT_ID;
        String redirectUri = KAKAO_SERVER_REDIRECT_URI;
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        // 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        // RestTemplate으로 POST 요청
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // 카카오로 액세스 토큰 요청
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

        // JSON 파싱 후 액세스 토큰 추출 (Jackson 라이브러리 사용)
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        String accessToken = jsonNode.get("access_token").asText();

        // 액세스 토큰을 활용한 로직 구현
        return "redirect:/home";
    }


}
