package com.example.mspersonalisation.entity;


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
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;

    @Column(nullable = false)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "bookmark")
    private Collection<BookRecipies> bookRecipies;

    @ManyToOne
    @JoinColumn(name="idLogin")
    private Login login;

}
