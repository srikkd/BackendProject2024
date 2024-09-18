package com.example.backendproject.services;

import com.example.backendproject.exceptions.NotFoundException;
import com.example.backendproject.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.models.Product;
import com.example.backendproject.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductServiceImpl implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductServiceImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient){
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
    public ResponseEntity<?> getProductById(Integer id) throws NotFoundException {
        return new ResponseEntity<>(
                convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.getProductById(id)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductServiceClient.getAllProducts();

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            genericProductDtos.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto));
        }

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addNewProduct(Product product) {

        GenericProductDto genericProductDto =
                convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.addNewProduct(product));

        ResponseEntity<?> responseEntity = ResponseEntity.ok().body(genericProductDto);
        return responseEntity;
    }

    @Override
    public ResponseEntity<?> updateProduct(int id, Product product) {

        GenericProductDto genericProductDto =
                convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.updateProduct(id, product));

        ResponseEntity<?> responseEntity = ResponseEntity.ok().body(genericProductDto);
        System.out.println("Updated product successfully");
        System.out.println(responseEntity);
        return responseEntity;
    }

    @Override
    public ResponseEntity<?> deleteProduct(Integer id) {

        GenericProductDto genericProductDto =
                convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.deleteProduct(id));

        ResponseEntity<?> responseEntity = ResponseEntity.ok().body(genericProductDto);
        System.out.println("Deleted product successfully");
        System.out.println(responseEntity);
        return responseEntity;
    }
}
