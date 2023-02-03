package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.NotesCommand;
import guru.springframework.sfgrecipes.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    /*private final RecipeToRecipeCommand recipeToRecipeCommand;

    public NotesToNotesCommand(RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }*/

    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) return null;

        //todo: implement recipe conversion
        return new NotesCommand()
                .setId(source.getId())
                .setRecipeNotes(source.getRecipeNotes());
    }
}
