package com.mct.recipeManagement.service;

import com.mct.recipeManagement.model.Recipe;
import com.mct.recipeManagement.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    RecipeRepo recipeRepo;

    public String createRecipePost(Recipe recipe) {
        recipeRepo.save(recipe);
        return "Recipe Created";
    }

    public boolean validRecipe(Recipe recipe) {
        return (recipe !=null && recipeRepo.existsById(recipe.getId()));
    }

    public Recipe getRecipe(Long recipeId) {
        Recipe recipe =recipeRepo.findById(recipeId).orElse(null);
        return recipe;
    }

    public void removeRecipe(Recipe recipe) {
        recipeRepo.delete(recipe);
    }

    public List<Recipe> getAllRecipe() {
        return recipeRepo.findAll();
    }

    public void updateRecipe(Recipe recipe, Recipe updateRecipe) {
        recipe.setName(updateRecipe.getName());
        recipe.setInstructions(updateRecipe.getInstructions());
        recipe.setIngredients(updateRecipe.getIngredients());
        recipeRepo.save(recipe);
    }

    public Recipe getRecipeByName(String recipeName) {
        Recipe recipe = recipeRepo.findFristByName(recipeName);
        if(recipe.getName().equals(recipeName)) {
            return recipe;
        }
        return recipe;
    }
}
