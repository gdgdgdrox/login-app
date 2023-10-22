package com.example.loginassignment.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.loginassignment.entity.User;
import com.example.loginassignment.model.CustomUserDetails;
import com.example.loginassignment.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optUser = userRepo.findByUsername(username);
        if (optUser.isPresent()){
            User user = optUser.get();
            Collection<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                    .toList();
                
            return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .authorities(authorities)
                .build();
        }
        else{
            throw new UsernameNotFoundException("Unable to find %s".formatted(username));
        }
    }


}
