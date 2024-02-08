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

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
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

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Recipe recipe = recipeService.likeRecipe(id, user);
        return recipe;
    }
}
