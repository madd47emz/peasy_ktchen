package com.example.mspersonalisation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogin;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "login")
    private Collection<Bookmark> bookmarks;

    @JsonIgnore
    @OneToOne(mappedBy = "login")
    private Review review;




}
