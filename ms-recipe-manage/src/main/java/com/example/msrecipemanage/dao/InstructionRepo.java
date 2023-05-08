package com.example.msrecipemanage.dao;

import com.example.msrecipemanage.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface InstructionRepo extends JpaRepository<Instruction,Long> {
}
