package com.example.msrecipemanage.dao;

import com.example.msrecipemanage.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RecipeRepo extends JpaRepository<Recipe,Long> {
    Recipe findRecipeByIdRecipe(Long id);
    boolean existsRecipeByName(String name);
    List<Recipe> findRecipesByNameContainsIgnoreCaseAndChefContainsIgnoreCase(String name,String chef);
    List<Recipe> findRecipesByNameContainsIgnoreCaseAndCategory_Name(String name,String category);
    @Query("select r from Recipe r join Tag t on r.idRecipe = t.recipe.idRecipe where t.content=:tag and r.name=:name")
    List<Recipe> findRecipesByTag(@Param("tag")String tag,@Param("name")String name);
    @Query("select r from Recipe r join Ingrediant i on r.idRecipe = i.recipe.idRecipe where i.name=:ing and r.name=:name")
    List<Recipe> findRecipesByIngrediant(@Param("ing")String ing,@Param("name")String name);


}
