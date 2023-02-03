package guru.springframework.sfgrecipes.commands;

public class NotesCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public NotesCommand setId(Long id) {
        this.id = id;
        return this;
    }

    public RecipeCommand getRecipe() {
        return recipe;
    }

    public NotesCommand setRecipe(RecipeCommand recipe) {
        this.recipe = recipe;
        return this;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public NotesCommand setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
        return this;
    }
}
