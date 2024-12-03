package com.example.swe_project.controller;

import com.example.swe_project.entity.Farmer;
import com.example.swe_project.services.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    // Create Farmer
    @PostMapping
    public Farmer createFarmer(@ModelAttribute Farmer farmer) {
        return farmerService.createFarmer(farmer);
    }

    // Get Farmer by ID
    @GetMapping("/{id}")
    public Farmer getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    // Get all Farmers
    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }


    // Update Farmer
    @PutMapping("/{id}")
    public Farmer updateFarmer(@PathVariable Long id, @ModelAttribute Farmer farmerDetails) {
        return farmerService.updateFarmer(id, farmerDetails);
    }

    // Delete Farmer
    @DeleteMapping("/{id}")
    public void deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
    }
}
