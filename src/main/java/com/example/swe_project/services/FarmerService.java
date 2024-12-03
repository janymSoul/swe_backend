package com.example.swe_project.services;

import com.example.swe_project.entity.Farmer;
import com.example.swe_project.entity.Role;
import com.example.swe_project.entity.UserStatus;
import com.example.swe_project.repo.FarmerRepository;
import com.example.swe_project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Farmer createFarmer(Farmer farmer) {
        farmer.setRole(Role.FARMER.name());
        farmer.setStatus(UserStatus.PENDING_APPROVAL.name());
        farmer.setEnabled(true);
        farmer.setPassword(passwordEncoder.encode(farmer.getPassword()));
        return (Farmer) farmerRepository.save(farmer);  // Saving as User will also store it as Farmer
    }

    public Farmer getFarmerById(Long id) {
        return (Farmer) farmerRepository.findById(id).orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll(); // Retrieve only farmers
    }

    public List<Farmer> getAllPendingFarmers() {
        return farmerRepository.findByStatus(UserStatus.PENDING_APPROVAL.name());
    }

    public Farmer updateFarmer(Long id, Farmer farmerDetails) {
        Farmer farmer = (Farmer) farmerRepository.findById(id).orElseThrow(() -> new RuntimeException("Farmer not found"));
        userService.updateUser(id, farmerDetails);
        if(farmerDetails.getFarmName() != null)
            farmer.setFarmName(farmerDetails.getFarmName());
        if(farmerDetails.getFarmLocation() != null)
            farmer.setFarmLocation(farmerDetails.getFarmLocation());
        if(farmerDetails.getFarmSize() != null)
            farmer.setFarmSize(farmerDetails.getFarmSize());
        if(farmerDetails.getFarmDescription() != null)
            farmer.setFarmDescription(farmerDetails.getFarmDescription());
        return (Farmer) farmerRepository.save(farmer);
    }

    // Delete Farmer
    public void deleteFarmer(Long id) {
        Farmer farmer = (Farmer) farmerRepository.findById(id).orElseThrow(() -> new RuntimeException("Farmer not found"));
        farmerRepository.delete(farmer);
    }

    public void approveFarmer(String username) {
        Farmer farmer = (Farmer) userRepository.findByUsername(username).stream().findFirst().orElseThrow(() -> new RuntimeException("Farmer not found"));
        farmer.setStatus(UserStatus.ACTIVE.name());
        farmerRepository.save(farmer);
        String text = "Your account has been approved, now you can post your products on our services. Glad to have you on board!!!";
        mailService.sendEmail(farmer.getEmail(), "Account Approval", text);
    }
}
