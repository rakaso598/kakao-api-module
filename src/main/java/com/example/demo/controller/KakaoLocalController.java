package com.example.demo.controller;

import com.example.demo.service.KakaoLocalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local")
public class KakaoLocalController {
    private final KakaoLocalService kakaoLocalService;

    public KakaoLocalController(KakaoLocalService kakaoLocalService) {
        this.kakaoLocalService = kakaoLocalService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam(name = "query") String query) {
        String result = kakaoLocalService.searchByKeyword(query);
        return ResponseEntity.ok(result);
    }
}
