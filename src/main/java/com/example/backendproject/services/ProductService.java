package com.example.backendproject.services;

import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.models.Product;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> getProductById(Integer id);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> addNewProduct(Product product);

    ResponseEntity<?> updateProduct(int id, GenericProductDto product);

    ResponseEntity<?> deleteProduct(Integer id);
}
