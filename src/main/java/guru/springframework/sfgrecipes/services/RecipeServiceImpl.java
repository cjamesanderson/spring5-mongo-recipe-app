package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.converters.RecipeCommandToRecipe;
import guru.springframework.sfgrecipes.converters.RecipeToRecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.exceptions.NotFoundException;
import guru.springframework.sfgrecipes.repositories.reactive.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    public Flux<Recipe> getRecipes() {
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {

        return recipeReactiveRepository.findById(id).map((recipe) -> {
            if (recipe == null) {
                throw new NotFoundException("Recipe Not Found. For ID value: " + id);
            }
            return recipe;
        });
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                    RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

                    recipeCommand.getIngredients().forEach(rc -> {
                        rc.setRecipeId(recipeCommand.getId());
                    });

                    return recipeCommand;
                });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        recipeReactiveRepository.deleteById(id).subscribe();
        return Mono.empty();
    }
}
