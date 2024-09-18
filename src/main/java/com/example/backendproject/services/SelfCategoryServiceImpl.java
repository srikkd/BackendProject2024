package com.example.backendproject.services;

import com.example.backendproject.models.Category;
import com.example.backendproject.repositories.CategoryRepository;
import com.example.backendproject.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfCategoryServiceImpl")
@RequiredArgsConstructor
public class SelfCategoryServiceImpl implements CategoryService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
//    public SelfCategoryServiceImpl(@Qualifier("categoryRepository") CategoryRepository categoryRepository){
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAllCategoryNames();
//        System.out.println(categoryList);
//        ResponseModel responseModel = new ResponseModel();
//        responseModel.setMessage("Fetched all categories successfully!!!");
//        responseModel.setData(categoryList);

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProductsinCategory(String categoryName) {

        return new ResponseEntity<>(productRepository.findAllByCategory(categoryName), HttpStatus.OK);
    }

    @Override
    public String addNewCategory(String category){
        Category c = new Category();
        c.setName(category);
        categoryRepository.save(c);
        System.out.println(c.getId());
        return "Created Category: " + category;
    }
}
