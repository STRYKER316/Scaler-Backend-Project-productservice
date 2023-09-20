package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    public Category(String name) {
        this.name = name;
    }

    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
