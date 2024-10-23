package com.example.demo.controller;

import com.example.demo.service.KakaoLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoLocalController {

    private final KakaoLocalService kakaoLocalService;

    @Autowired
    public KakaoLocalController(KakaoLocalService kakaoLocalService) {
        this.kakaoLocalService = kakaoLocalService;
    }

    @GetMapping("/search")
    public String searchKeyword(@RequestParam("query") String query) { // "query" 이름을 명시적으로 추가
        return kakaoLocalService.searchByKeyword(query);
    }
}