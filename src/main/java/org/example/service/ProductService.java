package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import java.util.Set;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();


    public void save( Product product) {

        productRepository.save(product);

        System.out.println("Product Service: saved -> " + product);
    }

    public Set<Product> getAll() {
        return productRepository.getAll();
    }

    public Set<Product> listAll() {
        return productRepository.getAll();
    }
}
