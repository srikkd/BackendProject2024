package com.example.backendproject.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericProductDto {
    private int id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
