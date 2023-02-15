package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String id);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteByRecipeIdAndIngredientId(String recipeId, String id);
}
