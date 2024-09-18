package com.example.backendproject.controllers;

import com.example.backendproject.dtos.ExceptionDto;
import com.example.backendproject.exceptions.NotFoundException;
import com.example.backendproject.models.Product;
import com.example.backendproject.services.FakeStoreProductServiceImpl;
import com.example.backendproject.services.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("selfProductServiceImpl")
    private ProductService productService;

//    @Autowired
//    private NotFoundException notFoundException;

    @Test
    void returnsNullWhenProductDoesntExist() throws NotFoundException {

        ResponseEntity<?> responseEntity = productController.getProductById(121);

        when(
                productService.getProductById(122)
        ).
                thenReturn(null);

        assertNull(responseEntity);
    }

    @Test
    void throwsExceptionWhenProductDoesntExist() throws NotFoundException {
//        when(
//                productService.getProductById(any())
//        )
//                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> productController.getProductById(123));
    }

//    @Test
//    void shouldReturnTitleNamanWithProductID1() throws NotFoundException {
//        Product product = new Product();
//        product.setTitle("Naman");
//        when(
//                productService.getProductById(1)
//        ).thenReturn(product);
//
//        assertEquals("Naman", productController.getProductById(1).getBody().getClass());
//    }
}
