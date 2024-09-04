package com.example.backendproject.services;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> getAllCategories();

    ResponseEntity<?> getProductsinCategory(String category);

    public String addNewCategory(String category);

}