package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.CategoryCommand;
import guru.springframeword.sfgrecipes.commands.IngredientCommand;
import guru.springframeword.sfgrecipes.commands.NotesCommand;
import guru.springframeword.sfgrecipes.domain.Difficulty;
import guru.springframeword.sfgrecipes.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private static final Integer PREP_TIME = 1;
    private static final Integer COOK_TIME = 2;
    private static final Integer SERVINGS = 3;
    private static final String SOURCE = "source";
    private static final String URL = "url";
    private static final String DIRECTIONS = "directions";
    private static final Long INGREDIENT_ID_1 = 2L;
    private static final Long INGREDIENT_ID_2 = 3L;
    private static final Long NOTES_ID = 4L;
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long CATEGORY_ID_1 = 5L;
    private static final Long CATEGORY_ID_2 = 6L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(),
                new CategoryCommandToCategory());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new guru.springframeword.sfgrecipes.commands.RecipeCommand()));
    }

    @Test
    void convert() {
        //given
        guru.springframeword.sfgrecipes.commands.RecipeCommand command = new guru.springframeword.sfgrecipes.commands.RecipeCommand()
                .setId(ID_VALUE)
                .setDescription(DESCRIPTION)
                .setPrepTime(PREP_TIME)
                .setCookTime(COOK_TIME)
                .setServings(SERVINGS)
                .setSource(SOURCE)
                .setUrl(URL)
                .setDirections(DIRECTIONS)
                .setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand().setId(NOTES_ID);
        command.setNotes(notesCommand);

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(new IngredientCommand().setId(INGREDIENT_ID_1));
        ingredientCommands.add(new IngredientCommand().setId(INGREDIENT_ID_2));
        command.setIngredients(ingredientCommands);

        CategoryCommand categoryCommand1 = new CategoryCommand().setId(CATEGORY_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand().setId(CATEGORY_ID_2);
        Set<CategoryCommand> categoryCommands = new HashSet<>();
        categoryCommands.add(categoryCommand1);
        categoryCommands.add(categoryCommand2);
        command.setCategories(categoryCommands);

        //when
        Recipe recipe = converter.convert(command);

        //assert
        assertNotNull(recipe);
        assertNotNull(recipe.getIngredients());
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getNotes());
        assertEquals(recipe.getId(), ID_VALUE);
        assertEquals(recipe.getDescription(), DESCRIPTION);
        assertEquals(recipe.getPrepTime(), PREP_TIME);
        assertEquals(recipe.getCookTime(), COOK_TIME);
        assertEquals(recipe.getServings(), SERVINGS);
        assertEquals(recipe.getSource(), SOURCE);
        assertEquals(recipe.getUrl(), URL);
        assertEquals(recipe.getDirections(), DIRECTIONS);
        assertEquals(recipe.getIngredients().size(), 2);
        assertEquals(recipe.getNotes().getId(), NOTES_ID);
        assertEquals(recipe.getDifficulty(), DIFFICULTY);
        assertEquals(recipe.getCategories().size(), 2);
    }
}