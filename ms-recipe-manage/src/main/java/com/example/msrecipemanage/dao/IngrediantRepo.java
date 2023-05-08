package com.example.msrecipemanage.dao;

import com.example.msrecipemanage.entity.Ingrediant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface IngrediantRepo extends JpaRepository<Ingrediant,Long> {
}
