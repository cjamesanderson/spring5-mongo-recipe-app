package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.converters.IngredientCommandToIngredient;
import guru.springframework.sfgrecipes.converters.IngredientToIngredientCommand;
import guru.springframework.sfgrecipes.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.sfgrecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.repositories.reactive.RecipeReactiveRepository;
import guru.springframework.sfgrecipes.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    private static final String RECIPE_ID = "1";
    private static final String ING_ID = "2";
    private static final String OLD_UOM_ID = "3";
    private static final String NEW_UOM_ID = "4";
    private static final String OLD_DESC = "old description";
    private static final String NEW_DESC = "new description";

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        service = new IngredientServiceImpl(ingredientToIngredientCommand,
                ingredientCommandToIngredient,
                recipeReactiveRepository,
                unitOfMeasureReactiveRepository);
    }

    @Test
    void testFindByRecipeIdAndId() {
        //given
        Recipe recipe = new Recipe().setId(RECIPE_ID);
        recipe.addIngredient(new Ingredient().setId(ING_ID));
        recipe.addIngredient(new Ingredient().setId("3"));
        Mono<Recipe> recipeMono = Mono.just(recipe);
        when(recipeReactiveRepository.findById(anyString())).thenReturn(recipeMono);

        //when
        IngredientCommand command = service.findByRecipeIdAndIngredientId(RECIPE_ID, ING_ID).block();

        //then
        assertNotNull(command);
        assertEquals(ING_ID, command.getId());
        //assertEquals(RECIPE_ID, command.getRecipeId());
    }

    @Test
    void testSaveIngredientCommandUpdate() {
        //given
        Recipe recipe = new Recipe()
                .setId(RECIPE_ID);
        UnitOfMeasure oldUom = new UnitOfMeasure()
                .setId(OLD_UOM_ID)
                .setDescription(OLD_DESC);
        Ingredient ingredient = new Ingredient()
                .setId(ING_ID)
                .setDescription(OLD_DESC)
                .setUom(oldUom);
        recipe.addIngredient(ingredient);

        UnitOfMeasure newUom = new UnitOfMeasure()
                .setId(NEW_UOM_ID)
                .setDescription(NEW_DESC);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand()
                .setId(NEW_UOM_ID)
                .setDescription(NEW_DESC);
        IngredientCommand command = new IngredientCommand()
                .setId(ING_ID)
                .setRecipeId(recipe.getId())
                .setDescription(NEW_DESC)
                .setUom(uomCommand);
        Mono<Recipe> recipeMono = Mono.just(recipe);
        Mono<UnitOfMeasure> unitOfMeasureMono = Mono.just(newUom);
        when(recipeReactiveRepository.findById(anyString())).thenReturn(recipeMono);
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(recipeMono);
        when(unitOfMeasureReactiveRepository.findById(anyString())).thenReturn(unitOfMeasureMono);

        //when
        IngredientCommand savedIngredient = service.saveIngredientCommand(command).block();

        //then
        assertNotNull(savedIngredient);
        assertEquals(NEW_DESC, savedIngredient.getDescription());
        assertEquals(NEW_DESC, savedIngredient.getUom().getDescription());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
        verify(unitOfMeasureReactiveRepository, times(1)).findById(anyString());
    }

    @Test
    void testSaveIngredientCommandNew() {
        //given
        Recipe recipe = new Recipe().setId(RECIPE_ID);
        IngredientCommand ingredientCommand = new IngredientCommand().setId(ING_ID).setRecipeId(RECIPE_ID);
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

        //when
        IngredientCommand savedIngredient = service.saveIngredientCommand(ingredientCommand).block();

        //then
        assertNotNull(savedIngredient);
        assertEquals(ING_ID, savedIngredient.getId());
        assertEquals(RECIPE_ID, savedIngredient.getRecipeId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testDeleteIngredient() {
        //given
        Recipe recipe = new Recipe().setId(RECIPE_ID);
        Ingredient ingredient = new Ingredient().setId(ING_ID);
        recipe.addIngredient(ingredient);
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(recipe));

        //when
        service.deleteByRecipeIdAndIngredientId(RECIPE_ID, ING_ID);

        //then
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

}