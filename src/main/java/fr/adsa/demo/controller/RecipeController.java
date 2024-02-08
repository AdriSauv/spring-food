package fr.adsa.demo.controller;

import fr.adsa.demo.model.Recipe;
import fr.adsa.demo.model.User;
import fr.adsa.demo.service.RecipeService;
import fr.adsa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{
        User user = userService.findUserById(userId);
        return recipeService.createRecipe(recipe, user);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{
        return recipeService.updateRecipe(recipe, id);
    }

    @GetMapping()
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable Long id) throws Exception{
        recipeService.deleteRecipe(id);
        return "Recipe deleted successfully";
    }

    @PutMapping("/like/{recipeId}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId) throws Exception{
        return recipeService.likeRecipe(recipeId, userId);
    }
}
