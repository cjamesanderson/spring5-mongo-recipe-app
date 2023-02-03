package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.CategoryCommand;
import guru.springframeword.sfgrecipes.domain.Category;
import guru.springframeword.sfgrecipes.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        // given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe().setId(LONG_VALUE));
        Category category = new Category()
                .setId(LONG_VALUE)
                .setDescription(DESCRIPTION)
                .setRecipes(recipes);

        //when
        CategoryCommand command = converter.convert(category);

        //then
        assertNotNull(command);
        assertEquals(category.getId(), LONG_VALUE);
        assertEquals(category.getDescription(), DESCRIPTION);
        //todo: implement recipe conversion tests
    }
}