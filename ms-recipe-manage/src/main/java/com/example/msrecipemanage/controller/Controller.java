package com.example.msrecipemanage.controller;

import com.example.msrecipemanage.dao.*;
import com.example.msrecipemanage.entity.*;
import com.example.msrecipemanage.model.Review;
import com.example.msrecipemanage.proxy.ReviewProx;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("recipe-management")
public class Controller {
    @Autowired
    CoverRepo coverRepo;
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
    @Autowired
    ReviewProx reviewProx;



    @GetMapping("/recipe/{id}/reviews")
    List<Review> getRecipeReviews(@PathVariable("id") Long id){

        return (List<Review>) reviewProx.getReviews(id).getContent();
    }
    @GetMapping("/recipe/{id}/instructions")
    List<Instruction> getRecipeInst(@PathVariable("id") Long id){

        return (List<Instruction>) recipeRepo.findById(id).get().getInstructions();
    }
    @GetMapping("/recipe/{id}/ingrediants")
    List<Ingrediant> getRecipeIng(@PathVariable("id") Long id){

        return (List<Ingrediant>) recipeRepo.findById(id).get().getIngrediants();
    }
    @GetMapping("/recipe/{id}/tags")
    List<Tag> getRecipeTags(@PathVariable("id") Long id){

        return (List<Tag>) recipeRepo.findById(id).get().getTags();
    }
    @GetMapping("/category/{id}/recipe")
    List<Recipe> getCategoryRecipe(@PathVariable("id") Long id){

        return (List<Recipe>) categoryRepo.findById(id).get().getRecipes();
    }
    @GetMapping("/categories")
    List<Category> getCategories(){

        return categoryRepo.findAll();
    }

    @GetMapping("/latestRecipes")
    List<Recipe> getLatestRecipes(){
        List<Recipe> l = recipeRepo.findAll();
        l = l.subList(l.size()-10, l.size()-1);


        return l;
    }
    @GetMapping("/recipe/{name}/search")
    List<Recipe> searchRecipe(@PathVariable("name") String name,
                              @RequestParam("category") String category,
                              @RequestParam("tag") String tag,
                              @RequestParam("ingrediant") String ingrediant,
                              @RequestParam("chef") String chef
    ){
        if(recipeRepo.existsRecipeByName(name)){
            if(chef!=""){return recipeRepo.findRecipesByNameContainsIgnoreCaseAndChefContainsIgnoreCase(name,chef);}
            if (tag!=""){return recipeRepo.findRecipesByTag(tag,name);}
            if (ingrediant!=""){return  recipeRepo.findRecipesByIngrediant(ingrediant,name);}
            if (category!=""){return recipeRepo.findRecipesByNameContainsIgnoreCaseAndCategory_Name(name,category);}

        }

        return null;
    }
    @GetMapping("/recipe/downloadCover/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable("id") Long id) {
        Cover image = coverRepo.findById(id).orElse(null);
        if (image != null) {
            ByteArrayResource resource = new ByteArrayResource(image.getData());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, image.getFileType());
            headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(image.getData().length));
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }}
    @PostMapping("/createRecipe")
    Recipe createNewRecipe(@RequestBody Recipe payload){
        return recipeRepo.save(payload);
    }
    @PostMapping("/recipe/{id}/addTag")
    Tag addTag(@RequestParam() String content,@PathVariable("id") Long idr){
        if (recipeRepo.existsById(idr)) {

            Tag tmp = new Tag();
            tmp.setContent(content);
            tmp.setRecipe(recipeRepo.findRecipeByIdRecipe(idr));
            return tagRepo.save(tmp);
        }
        return new Tag(null,"recipe not found",null);
    }
    @PostMapping("/recipe/{id}/addIngrediant")
    Ingrediant addIng(@RequestParam("name") String name,@RequestParam("amount") String amount,@PathVariable("id") Long idr){
        if (recipeRepo.existsById(idr)) {

            Ingrediant tmp = new Ingrediant();
            tmp.setName(name);
            tmp.setAmount(amount);
            tmp.setRecipe(recipeRepo.findRecipeByIdRecipe(idr));
            return ingrediantRepo.save(tmp);
        }
        return new Ingrediant(null,"recipe not found",null,null);
    }
    @PostMapping("/recipe/{id}/addInstruction")
    Instruction addInst(@RequestParam("content") String content,@PathVariable("id") Long idr){
        if (recipeRepo.existsById(idr)) {

            Instruction tmp = new Instruction();
            tmp.setContent(content);
            tmp.setRecipe(recipeRepo.findRecipeByIdRecipe(idr));
            return instructionRepo.save(tmp);
        }
        return new Instruction(null,"recipe not found",null);
    }
    @PostMapping("/recipe/{id}/uploadCover")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Long idr) throws  IOException {
        if (recipeRepo.existsById(idr)) {
            Cover image = new Cover();
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setData(file.getBytes());
            image = coverRepo.save(image);
            Recipe tmp = recipeRepo.findById(idr).get();
            tmp.setCover(image);
            recipeRepo.save(tmp);
            return ResponseEntity.ok("http://localhost:7777/peasy/recipeManagement/api/recipe/downloadCover/"+image.getId());
        }
        return ResponseEntity.notFound().build();
    }

}
