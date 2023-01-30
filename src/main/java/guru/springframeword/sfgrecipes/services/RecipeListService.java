package guru.springframeword.sfgrecipes.services;

import guru.springframeword.sfgrecipes.domain.Recipe;
import guru.springframeword.sfgrecipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeListService implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeListService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
