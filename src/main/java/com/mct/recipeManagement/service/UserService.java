package com.mct.recipeManagement.service;

import com.mct.recipeManagement.model.Comment;
import com.mct.recipeManagement.model.Recipe;
import com.mct.recipeManagement.model.User;
import com.mct.recipeManagement.repository.RecipeRepo;
import com.mct.recipeManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;

    @Autowired
    RecipeRepo recipeRepo;
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public String addUser(User user) {
        userRepo.save(user);
        return "User Added SuccessFully !!";
    }

    public String deleteUserById(Long userId) {
        boolean validId = userRepo.existsById(userId);
        if(validId){
            userRepo.deleteById(userId);
            return "User Deleted ";
        }else{
            return "User does not Exist ";
        }
    }

    public boolean authenticate(Long userId) {
        return userRepo.existsById(userId);
    }

    public String createRecipePost(Recipe recipe, Long userId) {
        User recipeOwner = userRepo.findFirstById(userId);
        recipe.setUser(recipeOwner);
        return recipeService.createRecipePost(recipe);

    }

    public String addComment(Comment comment, Long userId) {
        boolean validRecipe =recipeService.validRecipe(comment.getRecipe());
        if(validRecipe){
            User user = userRepo.findById(userId).orElse(null);
            comment.setUser(user);

            return commentService.saveComment(comment);
        }
        return "Invalid  post";
    }


    public String removeRecipeCommment(Long commenterId ,Long commentId) {
        Comment comment = commentService.findComment(commentId);
        if(comment != null){
            if(authorizeCommentRemove(comment,commenterId)){
                commentService.removeCommment(comment);
                return "Comment Deleted !!";

            }else {
                return "Unauthorized User !!";
            }

        }else{
            return "Commment Not Found !!";
        }
    }

    private boolean authorizeCommentRemove(Comment comment, Long commentId) {
        Long ownerId = comment.getRecipe().getUser().getId();
        Long commenterId = comment.getUser().getId();
        return ownerId.equals(commentId) || commenterId.equals(commentId);
    }

    public String removeRecipe(Long userId, Long recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if(recipe!=null){
            if(authorizeRecipeRemove(recipe,userId)){
                recipeService.removeRecipe(recipe);
                return "Recipe Deleted !!";

            }else {
                return "Unauthorized User !!";
            }

    }else{
        return "Recipe Not Found !!";
    }
    }

    private boolean authorizeRecipeRemove(Recipe recipe, Long userId) {
        Long ownerId = recipe.getUser().getId();
        return ownerId.equals(userId);
    }

    public List<Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    public String putRecipe(Long userId, Long recipeId,Recipe updateRecipe) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if(recipe !=null){
            if(authorizeRecipeRemove(recipe,userId)){
                recipeService.updateRecipe(recipe,updateRecipe);
                return "Recipe Updated !!";

            }else {
                return "Unauthorized User !!";
            }
        }
        return "Recipe not found !!";
    }

    public Recipe getRecipe(String recipeName) {
        return recipeService.getRecipeByName(recipeName);


    }
}
