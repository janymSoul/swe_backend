package com.example.swe_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
public class Farmer extends User {

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Farm Name'")
    private String farmName;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Farm Location'")
    private String farmLocation;
    private Double farmSize;  // Size in acres or square meters, etc.
    private String farmDescription;
}
