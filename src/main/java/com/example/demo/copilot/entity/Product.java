package com.example.demo.copilot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private int price;

    public Product() {
    }
}
