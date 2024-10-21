package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  // 이 클래스가 설정 클래스임을 명시
public class RestTemplateConfig {

    /*
        매번 템플릿을 생성하지 않고 한번 생성한 템플릿을
        재사용하기 위해 Bean으로 등록하는 설정 클래스
     */

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();  // RestTemplate 빈을 생성하여 리턴
    }
}
