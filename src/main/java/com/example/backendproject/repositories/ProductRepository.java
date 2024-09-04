package com.example.backendproject.repositories;

import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.models.Product;
import jakarta.transaction.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product P SET P.title = :title WHERE P.id = :id", nativeQuery = false)
    void updateById(int id, String title);

    Optional<List<Product>> findAllByCategory(String category);
}
