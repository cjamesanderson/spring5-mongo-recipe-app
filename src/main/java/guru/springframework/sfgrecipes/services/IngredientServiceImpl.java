package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.converters.IngredientCommandToIngredient;
import guru.springframework.sfgrecipes.converters.IngredientToIngredientCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.reactive.RecipeReactiveRepository;
import guru.springframework.sfgrecipes.repositories.reactive.UnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient, RecipeReactiveRepository recipeReactiveRepository, UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        return recipeReactiveRepository.findById(recipeId)
                .map(recipe -> recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(ingredient -> {
                    IngredientCommand command = ingredientToIngredientCommand.convert(ingredient.get());
                    command.setRecipeId(recipeId);
                    return command;
                });

//        Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(recipeId).blockOptional();
//
//        if (recipeOptional.isEmpty()) {
//            // todo: impl error handling
//            log.error("Recipe not found! ID: " + recipeId);
//        }
//
//        Recipe recipe = recipeOptional.get();
//
//        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
//                .filter(ingredient -> ingredient.getId().equals(ingredientId))
//                .map(ingredientToIngredientCommand::convert).findFirst();
//
//        if (ingredientCommandOptional.isEmpty()) {
//            // todo: impl error handling
//            log.error("Ingredient not found! ID: " + ingredientId);
//        }
//
//        // enhance command object with ingredient id
//        IngredientCommand ingredientCommand = ingredientCommandOptional.get();
//        ingredientCommand.setRecipeId(recipeId);
//
//        return Mono.just(ingredientCommand);
    }

    @Override
    @Transactional
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(command.getRecipeId()).blockOptional();

        if (recipeOptional.isEmpty()) {
            //todo: toss error if not found!
            System.out.println("LOGGING: Recipe not found! ID: " + command.getRecipeId());
            return Mono.just(new IngredientCommand());
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();
            if (ingredientOptional.isEmpty()) {
                //add new ingredient
                Ingredient ingredientNew = ingredientCommandToIngredient.convert(command);
                recipe.addIngredient(ingredientNew);
            } else {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureReactiveRepository
                        .findById(command.getUom().getId()).block());
                        //.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
                if (ingredientFound.getUom() == null) {
                    throw new RuntimeException("UOM NOT FOUND");
                }
            }

            Recipe recipeSaved = recipeReactiveRepository.save(recipe).block();

            Optional<Ingredient> ingredientOptionalSaved = recipeSaved.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

            //check by description
            if (ingredientOptionalSaved.isEmpty()) {
                //not totally safe.. but best guess
                ingredientOptionalSaved = recipeSaved.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //todo: check for fail
            IngredientCommand ingredientCommandSaved = ingredientToIngredientCommand.convert(ingredientOptionalSaved.get());
            ingredientCommandSaved.setRecipeId(recipe.getId());

            return Mono.just(ingredientCommandSaved);
        }
    }

    @Override
    public Mono<Void> deleteByRecipeIdAndIngredientId(String recipeId, String id) {
        Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(recipeId).blockOptional();

        if (recipeOptional.isEmpty()) {
            //todo: implement error handling
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst();
            if (ingredientOptional.isEmpty()) {
                //todo: implement error handling
            } else {
                Ingredient ingredient = ingredientOptional.get();
                recipe.getIngredients().remove(ingredient);
                recipeReactiveRepository.save(recipe).block();
            }
        }
        return Mono.empty();
    }
}
