package com.example.swe_project.controller;

import com.example.swe_project.entity.Farmer;
import com.example.swe_project.entity.User;
import com.example.swe_project.services.FarmerService;
import com.example.swe_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private UserService userService;

    @PostMapping("/approve-farmer/{username}")
    public void approveFarmer(@PathVariable String username) {
        farmerService.approveFarmer(username);
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get-pending-farmers")
    public List<Farmer> getPendingFarmers(){
        return farmerService.getAllPendingFarmers();
    }


}
