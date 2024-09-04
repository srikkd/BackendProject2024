package com.example.backendproject.repositories;

import com.example.backendproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "FROM Category")
    List<Category> findAllCategoryNames();
}
