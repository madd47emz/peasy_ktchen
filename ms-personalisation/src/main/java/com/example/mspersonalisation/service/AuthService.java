package com.example.mspersonalisation.service;

import com.example.mspersonalisation.dao.LoginRepo;
import com.example.mspersonalisation.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private LoginRepo repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        repository.save(login);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
