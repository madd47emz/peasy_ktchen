package com.example.mspersonalisation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;
    @Column(nullable = false,length = 1)
    private Long rating;
    private String comment;

    @OneToOne
    private Login login;

    private Long idRecipe;
}
