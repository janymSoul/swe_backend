package com.example.swe_project.controller;

import com.example.swe_project.dto.PurchaseDTO;
import com.example.swe_project.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/get-by-user/{userId}")
    public List<PurchaseDTO> getPurchaseByUserId(@PathVariable Long userId) {
        return purchaseService.getPurchasesByUserId(userId);
    }

    @PostMapping("/make")
    public String purchaseProduct(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return purchaseService.purchaseProduct(userId, productId, quantity);
    }
}
