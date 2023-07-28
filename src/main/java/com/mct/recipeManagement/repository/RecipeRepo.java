package com.mct.recipeManagement.repository;

import com.mct.recipeManagement.model.Recipe;
import com.mct.recipeManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    Recipe findFristByName(String recipeName);
}
