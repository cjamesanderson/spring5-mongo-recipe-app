package guru.springframework.sfgrecipes.domain;

import java.util.HashSet;
import java.util.Set;

public class Category {

    private String id;
    private String description;

    private Set<Recipe> recipes = new HashSet<>();

    public String getId() {
        return id;
    }

    public Category setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public Category setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }
}
