package com.example.msrecipemanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;

    @Column(unique = true,nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="idRecipe")
    private Recipe recipe;

}
