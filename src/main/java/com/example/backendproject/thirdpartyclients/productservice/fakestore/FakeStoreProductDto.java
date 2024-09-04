package com.example.backendproject.thirdpartyclients.productservice.fakestore;

import lombok.Data;

@Data
public class FakeStoreProductDto {
    private int id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
