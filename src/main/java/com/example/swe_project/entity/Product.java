package com.example.swe_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;
    private String category;
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;
}
