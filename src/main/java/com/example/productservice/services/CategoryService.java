package com.example.productservice.services;

import com.example.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    public Category getCategory(String uuid);
    public List<String> getProductTitles(List<String> categoryUUUIDs);
}
