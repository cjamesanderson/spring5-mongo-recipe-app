package guru.springframework.sfgrecipes.commands;

import java.util.HashSet;
import java.util.Set;

public class CategoryCommand {
    private Long id;
    private String description;

    private Set<RecipeCommand> recipes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public CategoryCommand setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<RecipeCommand> getRecipes() {
        return recipes;
    }

    public CategoryCommand setRecipes(Set<RecipeCommand> recipes) {
        this.recipes = recipes;
        return this;
    }
}