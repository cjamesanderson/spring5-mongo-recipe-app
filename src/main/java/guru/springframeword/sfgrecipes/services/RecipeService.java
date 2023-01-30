package guru.springframeword.sfgrecipes.services;

import guru.springframeword.sfgrecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
