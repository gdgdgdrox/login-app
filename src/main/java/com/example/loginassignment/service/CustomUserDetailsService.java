package com.example.loginassignment.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.loginassignment.model.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) {
        String query = "select username, password, enabled, full_name from users where username=?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, username);
        if (result.next()){
            String password = result.getString("password");
            boolean enabled = result.getBoolean("enabled");
            String fullName = result.getString("full_name");
            List<String> roles = loadUserAuthorities(username);
            Collection<GrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());
            CustomUserDetails customUserDetails = new CustomUserDetails(username,password,enabled,fullName,authorities);
            return customUserDetails;
        }
        else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    
    private List<String> loadUserAuthorities(String username){
        String queryRoles = "select authority from authorities where username=?";
        return jdbcTemplate.queryForList(queryRoles, String.class, username);
    }
}
