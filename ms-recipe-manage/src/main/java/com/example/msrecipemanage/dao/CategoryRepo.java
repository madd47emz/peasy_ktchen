package com.example.msrecipemanage.dao;

import com.example.msrecipemanage.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface CategoryRepo extends JpaRepository<Category,Long> {
}
