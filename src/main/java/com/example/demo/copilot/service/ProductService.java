package com.example.demo.copilot.service;

import com.example.demo.copilot.entity.Product;
import com.example.demo.copilot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveAndFlushProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public int getTotalPrice() {
        // get all products then sum up the price
        return productRepository.findAll().stream().mapToInt(Product::getPrice).sum();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsStartingWith(String letter) {
        return productRepository.findAll().stream().filter(product -> product.getName().startsWith(letter)).toList();
    }

    public List<Product> getProductCostingLessThan(int price) {
        return productRepository.findAll().stream().filter(product -> product.getPrice() < price).toList();
    }
}
