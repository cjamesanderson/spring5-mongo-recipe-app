package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(1);
    public static final Long UOM_ID = 2L;

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureCommand uom = new UnitOfMeasureCommand().setId(UOM_ID);
        IngredientCommand command = new IngredientCommand()
                .setId(ID_VALUE)
                .setDescription(DESCRIPTION)
                .setAmount(AMOUNT)
                .setUom(uom);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ingredient.getId(), ID_VALUE);
        assertEquals(ingredient.getDescription(), DESCRIPTION);
        assertEquals(ingredient.getAmount(), AMOUNT);
        assertEquals(ingredient.getUom().getId(), UOM_ID);
    }
}