package guru.springframework.sfgrecipes.domain;


public class Notes {

    private String id;

    private Recipe recipe;

    private String recipeNotes;

    public Notes() {}

    public Notes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public String getId() {
        return id;
    }

    public Notes setId(String id) {
        this.id = id;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Notes setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public Notes setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
        return this;
    }
}
