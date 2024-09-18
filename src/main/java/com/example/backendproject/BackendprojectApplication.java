package com.example.backendproject;

import com.example.backendproject.models.Category;
import com.example.backendproject.models.Product;
import com.example.backendproject.repositories.CategoryRepository;
import com.example.backendproject.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class })*/
//@EnableJpaAuditing
//@EnableAutoConfiguration
//@ComponentScan
//@EnableJpaRepositories
//@EntityScan
public class BackendprojectApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public BackendprojectApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendprojectApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Category c = new Category("electronics");
		categoryRepository.save(c);

		Product p = new Product(
				"Lenovo Ideapad",
				"Thin Laptop",
				"url.jpg",
				c,
				50000
		);
		productRepository.save(p);

	}
}
