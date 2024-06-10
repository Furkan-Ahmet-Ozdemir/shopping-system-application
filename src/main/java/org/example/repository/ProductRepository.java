package org.example.repository;


import org.example.model.Product;

import java.util.HashSet;
import java.util.Set;


public class ProductRepository {
    private Set<Product> productSet = new HashSet<>();

    public void save(Product product) {
        productSet.add(product);
    }

    public Set<Product> getAll() {
        return productSet;
    }
}
