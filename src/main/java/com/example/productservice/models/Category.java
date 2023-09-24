package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Fetch(FetchMode.SUBSELECT)  // SUBSELECT makes sure all categories are fetched in a single DB Query, it solves (N + 1) problem
    private List<Product> products;
}
