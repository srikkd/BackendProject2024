package com.example.backendproject.services;

import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.example.backendproject.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreCategoryServiceImpl")
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreCategoryServiceImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDto convertFakeStoreProductToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());

        return genericProductDto;
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        return fakeStoreProductServiceClient.getAllCategories();
    }

    @Override
    public ResponseEntity<?> getProductsinCategory(String category) {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductServiceClient.getProductsinCategory(category);
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            genericProductDtos.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto));
        }

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);
    }

    @Override
    public String addNewCategory(String category) {
        return null;
    }
}
