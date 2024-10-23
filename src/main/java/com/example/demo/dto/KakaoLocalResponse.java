package com.example.demo.dto;

import java.util.List;

public class KakaoLocalResponse {
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public static class Document {
        private String place_name;
        private String address_name;

        // Getters and setters
    }
}
