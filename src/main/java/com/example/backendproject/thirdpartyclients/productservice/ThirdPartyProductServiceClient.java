package com.example.backendproject.thirdpartyclients.productservice;

import com.example.backendproject.exceptions.NotFoundException;
import com.example.backendproject.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto getProductById(Integer id) throws NotFoundException;

    List<FakeStoreProductDto> getAllProducts();

    FakeStoreProductDto addNewProduct(Product product);

    FakeStoreProductDto updateProduct(int id, GenericProductDto product);

    FakeStoreProductDto deleteProduct(Integer id);

    public ResponseEntity<?> getAllCategories();

    public FakeStoreProductDto[] getProductsinCategory(String category);
}
