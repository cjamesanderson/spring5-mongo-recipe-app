package guru.springframework.sfgrecipes.commands;

import guru.springframework.sfgrecipes.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public RecipeCommand setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeCommand setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeCommand setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public Integer getServings() {
        return servings;
    }

    public RecipeCommand setServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public String getSource() {
        return source;
    }

    public RecipeCommand setSource(String source) {
        this.source = source;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RecipeCommand setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDirections() {
        return directions;
    }

    public RecipeCommand setDirections(String directions) {
        this.directions = directions;
        return this;
    }

    public Set<IngredientCommand> getIngredients() {
        return ingredients;
    }

    public RecipeCommand setIngredients(Set<IngredientCommand> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Byte[] getImage() {
        return image;
    }

    public RecipeCommand setImage(Byte[] image) {
        this.image = image;
        return this;
    }

    public NotesCommand getNotes() {
        return notes;
    }

    public RecipeCommand setNotes(NotesCommand notes) {
        this.notes = notes;
        if (notes != null) notes.setRecipe(this);
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public RecipeCommand setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Set<CategoryCommand> getCategories() {
        return categories;
    }

    public RecipeCommand setCategories(Set<CategoryCommand> categories) {
        this.categories = categories;
        return this;
    }
}
