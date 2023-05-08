package com.example.mspersonalisation.dao;

import com.example.mspersonalisation.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,Long> {
    List<Review> findReviewsByIdRecipe(Long id);
}
