package com.example.mspersonalisation.dao;

import com.example.mspersonalisation.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepo extends JpaRepository<Login,Long> {
    boolean existsByUsername(String username);
    Login findLoginByUsername(String username);
}
