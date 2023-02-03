package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
}
