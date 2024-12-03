package com.example.swe_project.services;

import com.example.swe_project.dto.PurchaseDTO;
import com.example.swe_project.entity.Product;
import com.example.swe_project.entity.Purchase;
import com.example.swe_project.entity.User;
import com.example.swe_project.repo.ProductRepository;
import com.example.swe_project.repo.PurchaseRepository;
import com.example.swe_project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<PurchaseDTO> getPurchasesByUserId(Long userId) {
        return purchaseRepository.findByUserId(userId).stream()
                .map(purchase -> new PurchaseDTO(
                        purchase.getId(),
                        purchase.getProduct().getName(),
                        purchase.getProduct().getPrice(),
                        purchase.getProduct().getFarmer().getUsername()
                ))
                .collect(Collectors.toList());
    }
    @Transactional
    public String purchaseProduct(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        if (product.getQuantity() == null || product.getQuantity() <= 0 || product.getQuantity() < quantity) {
            throw new RuntimeException("Product is out of stock.");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setTotalPrice(product.getPrice());
        purchaseRepository.save(purchase);

        return "Purchase successful for product: " + product.getName();
    }
}
