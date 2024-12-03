package com.example.swe_project.repo;

import com.example.swe_project.entity.Role;
import com.example.swe_project.entity.User;
import com.example.swe_project.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
}
