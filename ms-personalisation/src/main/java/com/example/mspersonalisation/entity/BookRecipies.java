package com.example.mspersonalisation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRecipies {
    @Id
    private Long idrecipes;
    @ManyToOne
    @JoinColumn(name="idBook")
    private Bookmark bookmark;

}
