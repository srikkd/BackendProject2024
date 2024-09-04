package com.example.backendproject.controllers;

import com.example.backendproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    @Qualifier("selfCategoryServiceImpl")
    private CategoryService categoryService;
//    public CategoryController(CategoryService categoryService){
//        this.categoryService = categoryService;
//    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getProductsinCategory(@PathVariable String category){
        return categoryService.getProductsinCategory(category);
    }

    @PostMapping("/{category}")
    public String addNewCategory(@PathVariable String category){
        return categoryService.addNewCategory(category);
    }
}
