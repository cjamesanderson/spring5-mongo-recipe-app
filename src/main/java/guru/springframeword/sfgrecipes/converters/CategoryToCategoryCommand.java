package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.CategoryCommand;
import guru.springframeword.sfgrecipes.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    /*private final RecipeToRecipeCommand recipeToRecipeCommand;

    public CategoryToCategoryCommand(RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }*/

    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) return null;

        //todo: implement recipe conversion
        return new CategoryCommand()
                .setId(source.getId())
                .setDescription(source.getDescription());
    }
}
