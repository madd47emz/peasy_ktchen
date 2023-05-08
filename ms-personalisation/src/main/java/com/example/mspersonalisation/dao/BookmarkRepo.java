package com.example.mspersonalisation.dao;

import com.example.mspersonalisation.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface BookmarkRepo extends JpaRepository<Bookmark,Long> {

}
