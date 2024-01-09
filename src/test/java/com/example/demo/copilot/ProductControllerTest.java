package com.example.demo.copilot;
import com.example.demo.copilot.controller.ProductController;
import com.example.demo.copilot.entity.Product;
import com.example.demo.copilot.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private Product expectedProduct;
    private List<Product> expectedProducts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        expectedProduct = new Product("1", "Product 1", 100);
        expectedProducts = Arrays.asList(
                new Product("1", "Product 1", 50),
                new Product("2", "Product 2", 75)
        );

        when(productService.getProductById("1")).thenReturn(expectedProduct);
        when(productService.getAllProducts()).thenReturn(expectedProducts);
        when(productService.getAllProductsStartingWith("P")).thenReturn(expectedProducts);
        when(productService.getProductCostingLessThan(100)).thenReturn(expectedProducts);
        when(productService.getTotalPrice()).thenReturn(125);
    }

    @Test
    public void getProductByIdReturnsExpectedProduct() {
        Product actualProduct = productController.getProductById("1");
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void getAllProductsReturnsExpectedProducts() {
        List<Product> actualProducts = productController.getAllProducts();
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void getAllProductsStartingWithReturnsExpectedProducts() {
        List<Product> actualProducts = productController.getAllProductsStartingWith("P");
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void getAllProductsLessThanReturnsExpectedProducts() {
        List<Product> actualProducts = productController.getAllProductsLessThan(100);
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void getTotalPriceReturnsExpectedTotal() {
        int actualTotal = productController.getTotalPrice();
        assertEquals(125, actualTotal);
    }
}