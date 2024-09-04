package com.example.backendproject.services;

import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.exceptions.NotFoundException;
import com.example.backendproject.models.Product;
import com.example.backendproject.repositories.ProductRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.persistence.Query;
import jakarta.transaction.*;
import org.hibernate.SharedSessionContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private SharedSessionContract session;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> getProductById(Integer id) throws NotFoundException {
        Optional<Product> p = productRepository.findById(id);

        if(p.isEmpty()){
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }
        return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
//        List<Product> productList = productRepository.findAll();
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addNewProduct(Product product) {
//        productRepository.save(product);

//        GenericProductDto product1 = new GenericProductDto();
//        product1.setId(product.getId());
//        product1.setTitle(product.getTitle());
//        product1.setDescription(product.getDescription());
//        product1.setImage(product.getImage());
//        product1.setCategory(product.getCategory());
//        product1.setPrice(product.getPrice());

        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    //improve updateProduct()
    @Override
    public ResponseEntity<?> updateProduct(int id, GenericProductDto product) {
        Product product1 = new Product(product.getTitle(), product.getDescription(), product.getCategory(), product.getImage(), product.getPrice());
        productRepository.updateById(id, product1.getTitle());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Product with id: " + id, HttpStatus.OK);
    }
}
