package guru.springframeword.sfgrecipes.services;

import guru.springframeword.sfgrecipes.commands.RecipeCommand;
import guru.springframeword.sfgrecipes.converters.RecipeCommandToRecipe;
import guru.springframeword.sfgrecipes.converters.RecipeToRecipeCommand;
import guru.springframeword.sfgrecipes.domain.Recipe;
import guru.springframeword.sfgrecipes.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "new description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    void testSaveOfDescription() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(savedRecipeCommand.getDescription(), NEW_DESCRIPTION);
        assertEquals(savedRecipeCommand.getId(), testRecipe.getId());
        assertEquals(savedRecipeCommand.getCategories().size(), testRecipe.getCategories().size());
        assertEquals(savedRecipeCommand.getIngredients().size(), testRecipe.getIngredients().size());
    }
}