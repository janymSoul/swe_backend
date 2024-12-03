package com.example.swe_project.seeders;

import com.example.swe_project.entity.Farmer;
import com.example.swe_project.entity.Role;
import com.example.swe_project.entity.User;
import com.example.swe_project.entity.UserStatus;
import com.example.swe_project.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public UserSeeder(UserRepository userRepository, FarmerRepository farmerRepository, PasswordEncoder passwordEncoder, PasswordResetTokenRepository passwordResetTokenRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.farmerRepository = farmerRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("STARTING SEEDING DATA");
        purchaseRepository.deleteAll();
        passwordResetTokenRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
        farmerRepository.deleteAll();
        productRepository.deleteAll();
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("user1");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole(Role.BUYER.name());
            user.setStatus(UserStatus.ACTIVE.name());
            user.setEmail("medet.tegistay@nu.edu.kz");
            userRepository.save(user);

            Farmer farmer = new Farmer();
            farmer.setUsername("farmer1");
            farmer.setPassword(passwordEncoder.encode("password"));
            farmer.setRole(Role.FARMER.name());
            farmer.setFarmName("Green Acres");
            farmer.setFarmLocation("Springfield");
            farmer.setEmail("medet.tegistay@nu.edu.kz");
            farmer.setStatus(UserStatus.PENDING_APPROVAL.name());
            farmerRepository.save(farmer);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN.name());
            admin.setEmail("medet.tegistay@nu.edu.kz");
            userRepository.save(admin);
        }
    }
}
