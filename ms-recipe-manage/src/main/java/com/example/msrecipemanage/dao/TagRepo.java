package com.example.msrecipemanage.dao;

import com.example.msrecipemanage.entity.Recipe;
import com.example.msrecipemanage.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag,Long> {


}
