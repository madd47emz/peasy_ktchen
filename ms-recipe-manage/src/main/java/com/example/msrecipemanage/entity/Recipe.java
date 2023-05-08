package com.example.msrecipemanage.entity;

import com.example.msrecipemanage.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.CollectionModel;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecipe;

    @Column(unique = true,nullable = false)
    private String name;
    @OneToOne
    private Cover cover;
    private String chef;
    private int cookTime;
    private int prepTime;

    @ManyToOne
    @JoinColumn(name="idCat")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private Collection<Tag> tags;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private Collection<Instruction> instructions;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private Collection<Ingrediant> ingrediants;

    @Transient
    private CollectionModel<Review> reviews;
}
