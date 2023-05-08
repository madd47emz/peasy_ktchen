package com.example.msrecipemanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediant;

    @Column(unique = true,nullable = false)
    private String name;
    private String amount;

    @ManyToOne
    @JoinColumn(name="idRecipe")
    private Recipe recipe;
}
