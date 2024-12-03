package com.example.swe_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseDTO {
    private Long purchaseId;
    private String productName;
    private Double productPrice;
    private String farmerName;
}
