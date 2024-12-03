package com.example.swe_project.repo;

import com.example.swe_project.entity.Farmer;
import com.example.swe_project.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    List<Farmer> findByFarmLocation(String location);  // Farm-specific queries
    List<Farmer> findByStatus(String status);     // Get farmers by status
}
