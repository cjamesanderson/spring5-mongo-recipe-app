package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.NotesCommand;
import guru.springframeword.sfgrecipes.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "recipe notes";

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        //given
        Notes notes = new Notes()
                .setId(ID_VALUE)
                .setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(command.getId(), ID_VALUE);
        assertEquals(command.getRecipeNotes(), RECIPE_NOTES);
        //todo: implement recipe conversion tests
    }
}