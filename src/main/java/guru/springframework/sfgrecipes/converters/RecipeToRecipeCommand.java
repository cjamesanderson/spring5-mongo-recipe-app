package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.CategoryCommand;
import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 NotesToNotesCommand notesToNotesCommand,
                                 CategoryToCategoryCommand categoryToCategoryCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) return null;

        final RecipeCommand command = new RecipeCommand()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setCookTime(source.getCookTime())
                .setDifficulty(source.getDifficulty())
                .setDirections(source.getDirections())
                .setNotes(notesToNotesCommand.convert(source.getNotes()))
                .setPrepTime(source.getPrepTime())
                .setServings(source.getServings())
                .setSource(source.getSource())
                .setImage(source.getImage())
                .setUrl(source.getUrl());

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            List<CategoryCommand> categoryCommands = new ArrayList<>();
            source.getCategories()
                    .forEach(category -> categoryCommands.add(categoryToCategoryCommand.convert(category)));
            command.setCategories(categoryCommands);
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            List<IngredientCommand> ingredientCommands = new ArrayList<>();
            source.getIngredients()
                    .forEach(ingredient -> ingredientCommands.add(ingredientToIngredientCommand.convert(ingredient)));
            command.setIngredients(ingredientCommands);
        }

        return command;
    }
}
