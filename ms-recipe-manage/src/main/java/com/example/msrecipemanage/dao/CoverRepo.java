package com.example.msrecipemanage.dao;

import com.example.msrecipemanage.entity.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface CoverRepo extends JpaRepository<Cover,Long> {
}
