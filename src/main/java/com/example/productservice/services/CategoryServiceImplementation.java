package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class CategoryServiceImplementation implements CategoryService {
    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository,
                                         ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<String> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryNames = new ArrayList<>();

        categories.forEach(
                category -> categoryNames.add(category.getName())
        );
        return categoryNames;
    }


    @Override
    public Category getCategoryById(String uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
        return categoryOptional.get();
    }


    @Override
    public List<String> getProductTitles(List<String> categoryUuidStrings) {
        List<UUID> uuids = new ArrayList<>();
        for(String categoryUUID : categoryUuidStrings) {
            uuids.add(UUID.fromString(categoryUUID));
        }

//        // Kinda bad code, may lead to Hibernate (N + 1) problem
//        List<Category> categories = categoryRepository.findAllByIdIn(uuids);
//
//        List<String> productTitles = new ArrayList<>();
//
//        categories.forEach(
//                category -> {
//                    category.getProducts().forEach(
//                            product -> productTitles.add(product.getTitle())
//                    );
//                }
//        );

        // Kinda good code, will try to avoid Hibernate (N + 1) problem
        List<Category> categories = categoryRepository.findAllByIdIn(uuids);
        List<Product> products = productRepository.findAllByCategoryIn(categories);

        List<String> productTitles = new ArrayList<>();
        for (Product product : products) {
            productTitles.add(product.getTitle());
        }

        return productTitles;
    }
}
