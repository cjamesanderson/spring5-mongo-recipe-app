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
import guru.springframework.sfgrecipes.repositories.RecipeRepository;
import guru.springframework.sfgrecipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    private UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    RecipeRepository recipeRepository;

    IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        service = new IngredientServiceImpl(ingredientToIngredientCommand,
                ingredientCommandToIngredient,
                recipeRepository,
                unitOfMeasureRepository);
    }

    @Test
    void testFindByRecipeIdAndId() {
        //given
        Recipe recipe = new Recipe().setId(RECIPE_ID);
        recipe.addIngredient(new Ingredient().setId(ING_ID));
        recipe.addIngredient(new Ingredient().setId("3"));
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        //when
        IngredientCommand command = service.findByRecipeIdAndIngredientId(RECIPE_ID, ING_ID);

        //then
        assertNotNull(command);
        assertEquals(command.getId(), ING_ID);
        assertEquals(command.getRecipeId(), RECIPE_ID);
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
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        Optional<UnitOfMeasure> uomOptional = Optional.of(newUom);
        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        when(unitOfMeasureRepository.findById(anyString())).thenReturn(uomOptional);

        //when
        IngredientCommand savedIngredient = service.saveIngredientCommand(command);

        //then
        assertNotNull(savedIngredient);
        assertEquals(NEW_DESC, savedIngredient.getDescription());
        assertEquals(NEW_DESC, savedIngredient.getUom().getDescription());
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        verify(unitOfMeasureRepository, times(1)).findById(anyString());
    }

    @Test
    void testSaveIngredientCommandNew() {
        //given
        Recipe recipe = new Recipe().setId(RECIPE_ID);
        IngredientCommand ingredientCommand = new IngredientCommand().setId(ING_ID).setRecipeId(RECIPE_ID);
        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        //when
        IngredientCommand savedIngredient = service.saveIngredientCommand(ingredientCommand);

        //then
        assertNotNull(savedIngredient);
        assertEquals(savedIngredient.getId(), ING_ID);
        assertEquals(savedIngredient.getRecipeId(), RECIPE_ID);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testDeleteIngredient() {
        //given
        Recipe recipe = new Recipe().setId(RECIPE_ID);
        Ingredient ingredient = new Ingredient().setId(ING_ID);
        recipe.addIngredient(ingredient);
        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));

        //when
        service.deleteByRecipeIdAndIngredientId(RECIPE_ID, ING_ID);

        //then
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

}