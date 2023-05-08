package com.example.mspersonalisation.controller;


import com.example.mspersonalisation.dao.BookRecipesRepo;
import com.example.mspersonalisation.dto.AuthRequest;
import com.example.mspersonalisation.dto.Recipe;
import com.example.mspersonalisation.entity.BookRecipies;
import com.example.mspersonalisation.entity.Bookmark;
import com.example.mspersonalisation.entity.Login;
import com.example.mspersonalisation.entity.Review;
import com.example.mspersonalisation.proxy.RecipeProx;
import com.example.mspersonalisation.dao.BookmarkRepo;
import com.example.mspersonalisation.dao.LoginRepo;
import com.example.mspersonalisation.dao.ReviewRepo;
import com.example.mspersonalisation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("personalisation")
public class Controller {
    @Autowired
    LoginRepo loginRepo;
    @Autowired
    BookmarkRepo bookmarkRepo;
    @Autowired
    BookRecipesRepo bookRecipesRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    RecipeProx recipeProx;

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("This endpoint can only be accessed by users with ROLE_ADMIN");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("This endpoint can only be accessed by users with ROLE_USER");
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody Login user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
    //user bookmarks
    @GetMapping("/login/{username}/bookmarks")
    List<Bookmark> getUserBookmarks(@PathVariable("username") String username){
        if(loginRepo.existsByUsername(username)){
            Login u = loginRepo.findLoginByUsername(username);
            return (List<Bookmark>) u.getBookmarks();

        }
        return null;
    }
    @GetMapping("/bookmark/{id}/recipes")
    List<Recipe> getBookmarkRecipes(@PathVariable("id") Long id){
        Bookmark b = bookmarkRepo.findById(id).get();
        List<Recipe> recipeList = new ArrayList<>();
        List<BookRecipies> list = (ArrayList<BookRecipies>) b.getBookRecipies();
        for (BookRecipies br:list
             ) {
            if(recipeProx.getRecipe(br.getIdrecipes())!=null){
                recipeList.add(recipeProx.getRecipe(br.getIdrecipes()));
            }

        }

        return null;
    }
    @GetMapping("/recipe/{id}/reviews")
    List<Review> getRecipeReviews(@PathVariable("id") Long id){

            if(recipeProx.getRecipe(id)!=null){
                return reviewRepo.findReviewsByIdRecipe(id);
            }
        return null;
    }



    @PostMapping("/login/{username}/bookmarks/createBook")
    public Bookmark createNewBook(@RequestBody Bookmark payload,@PathVariable("username") String username) {
        if(loginRepo.existsByUsername(username)){
            Login u = loginRepo.findLoginByUsername(username);
            payload.setLogin(u);
            return bookmarkRepo.save(payload);

        }
        return null;
    }

    @PostMapping("/bookmark/{id}/addRecipe/{idr}")
    public BookRecipies AddRecipeToBook(@PathVariable("id") Long id,@PathVariable("idr") Long idr) {
        BookRecipies r= new BookRecipies();
        r.setIdrecipes(idr);
        r.setBookmark(bookmarkRepo.findById(id).get());

        return bookRecipesRepo.save(r);
    }
    @PostMapping("review/create/login/{username}")
    public Review createNewReview(@RequestBody Review payload,@PathVariable("username") String username){
        if(loginRepo.existsByUsername(username)){
            Login u = loginRepo.findLoginByUsername(username);
            payload.setLogin(u);
            return reviewRepo.save(payload);

        }
        return null;
    }


    @PutMapping("/review/{id}/update")
    public Review updateReview(@PathVariable(value = "id") Long idrev,
                                 @RequestBody Review payload) {

        if (reviewRepo.findById(idrev).isPresent()) {return reviewRepo.save(payload);}
        payload.setComment("review does not exist");
        return payload;
    }

    @DeleteMapping("/login/{username}/bookmark/{id}/delete")
    public String DeleteBookFromUser(@PathVariable(value = "username") String username,@PathVariable(value = "id") Long idBook){

        if (loginRepo.existsByUsername(username) && bookmarkRepo.findById(idBook).isPresent()){
            bookmarkRepo.deleteById(idBook);
            return "Correctly deleted";}
        return "the IDs is not valid";
    }
    @DeleteMapping("/bookmark/{id}/deleteRecipe/{idr}")
    public String DeleteRecipeFromBook(@PathVariable(value = "id") Long id,@PathVariable(value = "idr") Long idr){

        if (bookRecipesRepo.findById(idr).isPresent() && bookmarkRepo.findById(id).isPresent()){
            bookRecipesRepo.deleteById(idr);
            return "Correctly deleted";}
        return "the IDs is not valid";
    }





}
