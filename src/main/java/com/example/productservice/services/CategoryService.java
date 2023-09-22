package com.example.productservice.services;

import com.example.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    public List<String> getCategories();
    public Category getCategoryById(String uuid);
    public List<String> getProductTitles(List<String> categoryUUUIDs);
}
