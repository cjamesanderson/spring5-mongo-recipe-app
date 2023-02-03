package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.NotesCommand;
import guru.springframeword.sfgrecipes.domain.Notes;
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
