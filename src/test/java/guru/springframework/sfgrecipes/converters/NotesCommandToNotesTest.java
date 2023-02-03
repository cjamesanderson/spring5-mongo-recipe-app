package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.NotesCommand;
import guru.springframework.sfgrecipes.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "recipe notes";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //given
        NotesCommand command = new NotesCommand()
                .setId(ID_VALUE)
                .setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(notes.getId(), ID_VALUE);
        assertEquals(notes.getRecipeNotes(), RECIPE_NOTES);
        //todo: implement recipe conversion tests
    }
}