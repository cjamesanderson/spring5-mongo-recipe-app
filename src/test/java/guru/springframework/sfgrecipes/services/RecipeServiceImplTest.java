package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.converters.*;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.exceptions.NotFoundException;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository,
                new RecipeToRecipeCommand(
                        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                        new NotesToNotesCommand(),
                        new CategoryToCategoryCommand()),
                new RecipeCommandToRecipe(
                        new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                        new NotesCommandToNotes(),
                        new CategoryCommandToCategory()
                ));
    }

    @Test
    void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById("1");

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipesTest() {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() throws Exception {

        String idToDelete = "2";

        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyString());
    }

    @Test
    void testGetRecipeIdNotFound() throws Exception {
        String recipeId = "1";

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> recipeService.findById(recipeId), "Expected NotFoundException not thrown.");

        assertEquals("Recipe Not Found. For ID value: " + recipeId, thrown.getMessage());
    }
}