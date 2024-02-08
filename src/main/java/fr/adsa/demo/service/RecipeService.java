package fr.adsa.demo.service;

import fr.adsa.demo.model.Recipe;
import fr.adsa.demo.model.User;

import java.util.List;

public interface RecipeService {
    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id)throws Exception;
    public void deleteRecipe(Long id) throws Exception;
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
    public List<Recipe> getAllRecipes();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
