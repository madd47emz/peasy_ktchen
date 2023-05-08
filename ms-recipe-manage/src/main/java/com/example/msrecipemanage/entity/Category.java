package com.example.msrecipemanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;

    @Column(unique = true,nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Collection<Recipe> recipes;
}
