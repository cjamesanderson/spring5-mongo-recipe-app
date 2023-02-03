package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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


    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        //given
        Recipe recipe = new Recipe()
                .setId(ID_VALUE)
                .setDescription(DESCRIPTION)
                .setPrepTime(PREP_TIME)
                .setCookTime(COOK_TIME)
                .setServings(SERVINGS)
                .setSource(SOURCE)
                .setUrl(URL)
                .setDirections(DIRECTIONS)
                .setDifficulty(DIFFICULTY);

        Notes notes = new Notes().setId(NOTES_ID);
        recipe.setNotes(notes);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient().setId(INGREDIENT_ID_1));
        ingredients.add(new Ingredient().setId(INGREDIENT_ID_2));
        recipe.setIngredients(ingredients);

        Category category1 = new Category().setId(CATEGORY_ID_1);
        Category category2 = new Category().setId(CATEGORY_ID_2);
        Set<Category> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);
        recipe.setCategories(categories);

        //when
        RecipeCommand command = converter.convert(recipe);

        //assert
        assertNotNull(command);
        assertNotNull(command.getIngredients());
        assertNotNull(command.getCategories());
        assertNotNull(command.getNotes());
        assertEquals(command.getId(), ID_VALUE);
        assertEquals(command.getDescription(), DESCRIPTION);
        assertEquals(command.getPrepTime(), PREP_TIME);
        assertEquals(command.getCookTime(), COOK_TIME);
        assertEquals(command.getServings(), SERVINGS);
        assertEquals(command.getSource(), SOURCE);
        assertEquals(command.getUrl(), URL);
        assertEquals(command.getDirections(), DIRECTIONS);
        assertEquals(command.getIngredients().size(), 2);
        assertEquals(command.getNotes().getId(), NOTES_ID);
        assertEquals(command.getDifficulty(), DIFFICULTY);
        assertEquals(command.getCategories().size(), 2);
    }
}