package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.converters.*;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.exceptions.NotFoundException;
import guru.springframework.sfgrecipes.repositories.reactive.RecipeReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeReactiveRepository,
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
        Mono<Recipe> recipeOptional = Mono.just(recipe);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(recipeOptional);

        Mono<Recipe> recipeReturned = recipeService.findById("1");

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    void getRecipesTest() {

        Recipe recipe = new Recipe();
        Flux<Recipe> recipeData = Flux.just(recipe);

        when(recipeReactiveRepository.findAll()).thenReturn(recipeData);

        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(recipes.size(), 1);
        verify(recipeReactiveRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() throws Exception {

        String idToDelete = "2";

        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());

        recipeService.deleteById(idToDelete).block();

        verify(recipeReactiveRepository, times(1)).deleteById(anyString());
    }

    @Disabled
    @Test
    void testGetRecipeIdNotFound() throws Exception {
        String recipeId = "1";

        Mono<Recipe> recipeMono = Mono.empty();

        when(recipeReactiveRepository.findById(anyString())).thenReturn(recipeMono);

        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> recipeService.findById(recipeId).block(), "Expected NotFoundException not thrown.");

        assertEquals("Recipe Not Found. For ID value: " + recipeId, thrown.getMessage());
    }
}