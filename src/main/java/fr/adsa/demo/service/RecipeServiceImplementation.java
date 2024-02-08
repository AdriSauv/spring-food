package fr.adsa.demo.service;

import fr.adsa.demo.model.Recipe;
import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService{

    private RecipeRepository recipeRepository;

    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe savedRecipe = new Recipe();
        savedRecipe.setTitle(recipe.getTitle());
        savedRecipe.setUser(user);
        savedRecipe.setImage(recipe.getImage());
        savedRecipe.setDescription(recipe.getDescription());
        savedRecipe.setVegetarian(recipe.isVegetarian());
        savedRecipe.setCreatedDate(LocalDateTime.now());

        return recipeRepository.save(savedRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
            return recipe.get();
        }else{
            throw new Exception("Recipe not found");
        }
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);
        if(recipe.getTitle()!=null){
            oldRecipe.setTitle(recipe.getTitle());
        }
        if(recipe.getImage()!=null){
            oldRecipe.setImage(recipe.getImage());
        }
        if(recipe.getDescription()!=null){
            oldRecipe.setDescription(recipe.getDescription());
        }
        if(recipe.isVegetarian()!=oldRecipe.isVegetarian()){
            oldRecipe.setVegetarian(recipe.isVegetarian());
        }
        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, Long userId) throws Exception {
        Recipe recipe = findRecipeById(recipeId);
        if(recipe.getLikes().contains(userId)){
            recipe.getLikes().remove(userId);
        }else{
            recipe.getLikes().add(userId);
        }
        return recipeRepository.save(recipe);
    }
}
