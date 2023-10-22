package com.example.loginassignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loginassignment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    Optional<User> findByUsername(String username);
}
