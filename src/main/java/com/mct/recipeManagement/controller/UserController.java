package com.mct.recipeManagement.controller;

import com.mct.recipeManagement.model.Comment;
import com.mct.recipeManagement.model.Recipe;
import com.mct.recipeManagement.model.User;
import com.mct.recipeManagement.model.ValidationUser;
import com.mct.recipeManagement.service.CommentService;
import com.mct.recipeManagement.service.UserService;
import com.mct.recipeManagement.service.ValidationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ValidationService validationService;
    @Autowired
    CommentService commentService;

    @GetMapping("user")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("user/add")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("user/delete/{userId}")
    public String deleteUserById(@PathVariable Long userId){
        return userService.deleteUserById(userId);
    }

    @PostMapping("create/recipe")
    public String createRecipePost(@RequestBody Recipe recipe , @RequestParam Long userId){
        if(validationService.authenticate(userId)) {
            return userService.createRecipePost(recipe,userId);
        }
        else{
            return "Not found user !!!";
        }
    }
    @PostMapping("recipe/comment")
    public String addComment(@RequestBody Comment comment , @RequestParam Long userId){
        return userService.addComment(comment,userId);
    }
    @DeleteMapping("recipe/comment/delete")
    public String removeRecpiCommment(@RequestParam Long commenterId,@RequestParam Long commentId){
        if(validationService.authenticate(commenterId)) {
            return userService.removeRecipeCommment(commenterId, commentId);
        }
        else{
            return "Not found user !!!";
        }
    }
    @DeleteMapping("recipe/delete")
    public String removeRecipe(@RequestParam Long userId,@RequestParam Long recipeId){
        if(validationService.authenticate(userId)) {
            return userService.removeRecipe(userId, recipeId);
        }
        else{
            return "Not found OwnerUser !!!";
        }
    }
    @GetMapping("recipes")
    public List<Recipe> getAllRecipe(){
        return userService.getAllRecipe();
    }
    @GetMapping("recipe/by/name")
    public Recipe getRecipe(@RequestParam String recipeName){
        return userService.getRecipe(recipeName);
    }
    @PutMapping("user/update/own/recipe")
    public String putRecipe(@RequestParam Long recipeId,@RequestParam Long userId,@RequestBody Recipe updateRecipe){
        if(validationService.authenticate(userId)) {
            return userService.putRecipe(userId, recipeId,updateRecipe);
        }
        else{
            return "Not found User !!!";
        }
    }

}
