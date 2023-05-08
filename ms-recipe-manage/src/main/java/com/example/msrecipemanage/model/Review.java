package com.example.msrecipemanage.model;

import lombok.Data;

@Data
public class Review {
    private Long rating;
    private String comment;
    private String username;
}
