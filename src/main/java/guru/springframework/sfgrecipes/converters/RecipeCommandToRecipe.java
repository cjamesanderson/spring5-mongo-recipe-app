package guru.springframework.sfgrecipes.converters;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Category;
import guru.springframework.sfgrecipes.domain.Ingredient;
import guru.springframework.sfgrecipes.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes,
                                 CategoryCommandToCategory categoryCommandToCategory) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) return null;

        Recipe recipe = new Recipe()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setCookTime(source.getCookTime())
                .setDifficulty(source.getDifficulty())
                .setDirections(source.getDirections())
                .setNotes(notesCommandToNotes.convert(source.getNotes()))
                .setPrepTime(source.getPrepTime())
                .setServings(source.getServings())
                .setSource(source.getSource())
                .setUrl(source.getUrl());

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            Set<Category> categories = new HashSet<>();
            source.getCategories()
                    .forEach(categoryCommand -> categories.add(categoryCommandToCategory.convert(categoryCommand)));
            recipe.setCategories(categories);
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            Set<Ingredient> ingredients = new HashSet<>();
            source.getIngredients()
                    .forEach(ingredientCommand -> ingredients.add(ingredientCommandToIngredient.convert(ingredientCommand)));
            recipe.setIngredients(ingredients);
        }

        return recipe;
    }
}
