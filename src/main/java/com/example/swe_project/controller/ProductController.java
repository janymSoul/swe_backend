package com.example.swe_project.controller;

import com.example.swe_project.entity.Product;
import com.example.swe_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping
    public Product createProduct(@ModelAttribute Product product) {
        return productService.createProduct(product);
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Get Products by Farmer ID
    @GetMapping("/farmer/{farmerId}")
    public List<Product> getProductsByFarmerId(@PathVariable Long farmerId) {
        return productService.getProductsByFarmerId(farmerId);
    }

    // Update Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @ModelAttribute Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
