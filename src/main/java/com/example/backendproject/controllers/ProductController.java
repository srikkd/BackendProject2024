package com.example.backendproject.controllers;

import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.exceptions.NotFoundException;
import com.example.backendproject.models.Product;
import com.example.backendproject.services.ProductService;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductServiceImpl") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody GenericProductDto product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        return productService.deleteProduct(id);
    }
}
