package guru.springframeword.sfgrecipes.controllers;

import guru.springframeword.sfgrecipes.services.RecipeListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeListService recipeListService;

    public IndexController(RecipeListService recipeListService) {
        this.recipeListService = recipeListService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeListService.getRecipes());

        return "index";
    }
}
