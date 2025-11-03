package com.oswizar.springbootsample.service;

import com.oswizar.springbootsample.entity.Product;
import com.oswizar.springbootsample.mapper.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveAllProducts(List<Product> products) {
        return (List<Product>) productRepository.saveAll(products);
    }

    public List<Product> getAllProducts() {
        Iterable<Product> productIterable = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        productIterable.forEach(products::add);
        return products;
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}