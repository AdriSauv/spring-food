package fr.adsa.demo.controller;

import fr.adsa.demo.model.Recipe;
import fr.adsa.demo.model.User;
import fr.adsa.demo.service.RecipeService;
import fr.adsa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/recipe/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{
        User user = userService.findUserById(userId);
        return recipeService.createRecipe(recipe, user);
    }

    @PutMapping("/api/recipe/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{
        return recipeService.updateRecipe(recipe, id);
    }

    @GetMapping("/api/recipe")
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @DeleteMapping("/api/recipe/{id}")
    public String deleteRecipe(@PathVariable Long id) throws Exception{
        recipeService.deleteRecipe(id);
        return "Recipe deleted successfully";
    }

    @PutMapping("/api/recipe/like/{recipeId}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId) throws Exception{
        return recipeService.likeRecipe(recipeId, userId);
    }
}
