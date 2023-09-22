package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.repositories.CategoryRepository;
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

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(String uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
        return categoryOptional.get();
    }


    @Override
    public List<String> getProductTitles(List<String> categoryUuidStrings) {
        List<UUID> uuids = new ArrayList<>();
        for(String categoryUUID : categoryUuidStrings) {
            uuids.add(UUID.fromString(categoryUUID));
        }

        List<Category> categories = categoryRepository.findAllByIdIn(uuids);

        List<String> productTitles = new ArrayList<>();

        categories.forEach(
                category -> {
                    category.getProducts().forEach(
                            product -> productTitles.add(product.getTitle())
                    );
                }
        );

        return productTitles;
    }
}
