package com.example.productservice;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	public ProductserviceApplication(CategoryRepository categoryRepository,
									 ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Category category = new Category();
//		category.setName("Apple Phone");
//		Category savedCategory = categoryRepository.save(category);
//
//		Product product = new Product();
//		product.setTitle("I-phone 15");
//		product.setDescription("I-phone...lol");
//		product.setCategory(savedCategory);
//		productRepository.save(product);

		Category category1 = categoryRepository.findById(UUID.fromString("bdcbb858-70e7-4c17-9414-d03233aa6944")).get();
		System.out.println("Category name is: " + category1.getName());
	}
}
