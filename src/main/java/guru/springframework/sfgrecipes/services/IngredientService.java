package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import reactor.core.publisher.Mono;

public interface IngredientService {
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String id);
    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);
    Mono<Void> deleteByRecipeIdAndIngredientId(String recipeId, String id);
}
