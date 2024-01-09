package com.example.demo.copilot.controller;

import com.example.demo.copilot.entity.Product;
import com.example.demo.copilot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get/product/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        Product product = productService.getProductById(id);
        System.out.println(product.toString());
        return productService.getProductById(id);
    }

    @PostMapping("/save/product/{id}")
    public void saveProduct(@PathVariable("id") String id,
                            @RequestParam("name") String name,
                            @RequestBody int price) {
        Product product = new Product(id, name, price);
        productService.saveAndFlushProduct(product);
    }

    @DeleteMapping("/delete/product/{id}")
    public void deleteProduct(String id) {
        productService.deleteProduct(id);
    }

    // Endpoint to obtain total price of all products
    @GetMapping("/get/total")
    public int getTotalPrice() {
        return productService.getTotalPrice();
    }

    // Endpoint to get list of all products
    @GetMapping("/get/product/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Endpoint to get list of all products starting with a specific letter
    @GetMapping("/get/product/all/{letter}")
    public List<Product> getAllProductsStartingWith(@PathVariable("letter") String letter) {
        return productService.getAllProductsStartingWith(letter);
    }

    // Endpoint to get list of all products costing less than a specific price
    @GetMapping("/get/product/less/{price}")
    public List<Product> getAllProductsLessThan(@PathVariable("price") int price) {
        return productService.getProductCostingLessThan(price);
    }
}
