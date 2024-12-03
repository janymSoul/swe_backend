package com.example.swe_project.services;

import com.example.swe_project.entity.Product;
import com.example.swe_project.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Create Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Read Product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProductsByFarmerId(Long farmerId) {
        return productRepository.findByFarmerId(farmerId);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if(productDetails.getName() != null)
            product.setName(productDetails.getName());
        if(productDetails.getCategory() != null)
            product.setCategory(productDetails.getCategory());
        if(productDetails.getDescription() != null)
            product.setDescription(productDetails.getDescription());
        if(productDetails.getPrice() != null)
            product.setPrice(productDetails.getPrice());
        if(productDetails.getQuantity() != null)
            product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }

    // Delete Product
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

}
