package com.example.mspersonalisation.proxy;

import com.example.mspersonalisation.dto.Recipe;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms-recipe-manage")
@LoadBalancerClient(name="ms-recipe-manage")
public interface RecipeProx {
    @GetMapping("/recipes/search/findRecipeByIdRecipe")
    public Recipe getRecipe(@RequestParam("id") Long id);
}
