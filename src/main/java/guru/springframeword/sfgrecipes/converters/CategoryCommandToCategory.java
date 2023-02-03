package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.CategoryCommand;
import guru.springframeword.sfgrecipes.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    /*
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public CategoryCommandToCategory(RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }*/

    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) { return null; }

        //todo: implement recipe parameter conversion
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
