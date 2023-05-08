package com.example.msrecipemanage;

import com.example.msrecipemanage.dao.*;
import com.example.msrecipemanage.entity.Category;
import com.example.msrecipemanage.entity.Recipe;
import com.example.msrecipemanage.proxy.ReviewProx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRecipeManageApplication implements CommandLineRunner {

    @Autowired
    RecipeRepo recipeRepo;
    @Autowired
    IngrediantRepo ingrediantRepo;
    @Autowired
    InstructionRepo instructionRepo;
    @Autowired
    TagRepo tagRepo;
    @Autowired
    CategoryRepo categoryRepo;


    public static void main(String[] args) {
        SpringApplication.run(MsRecipeManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Category c1 = categoryRepo.save(new Category(null,"cakes",null));
//        Category c2 = categoryRepo.save(new Category(null,"soup",null));
//        Category c3 = categoryRepo.save(new Category(null,"pasta",null));
//        Recipe r1 = recipeRepo.save(new Recipe(null,"cookie pie",null,"chef mennoura",30,20,c1,null,null,null,null));
//        Recipe r2 = recipeRepo.save(new Recipe(null,"carrot soup",null,"chef mennoura",45,15,c2,null,null,null,null));
//        Recipe r3 = recipeRepo.save(new Recipe(null,"japanese onion soup",null,"chef mennoura",45,15,c2,null,null,null,null));
//        Recipe r4 = recipeRepo.save(new Recipe(null,"pasta with chicken",null,"chef mennoura",20,10,c3,null,null,null,null));



    }
}
