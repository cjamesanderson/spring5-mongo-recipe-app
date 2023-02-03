package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.NotesCommand;
import guru.springframework.sfgrecipes.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    /*private final RecipeCommandToRecipe recipeCommandToRecipe;

    public NotesCommandToNotes(RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }*/

    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) return null;

        //todo: implement recipe conversion
        return new Notes()
                .setId(source.getId())
                .setRecipeNotes(source.getRecipeNotes());
    }
}
