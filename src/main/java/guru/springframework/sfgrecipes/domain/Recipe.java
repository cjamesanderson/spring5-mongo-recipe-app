package guru.springframework.sfgrecipes.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
public class Recipe {

    @Id
    private String id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;

    private Notes notes;
    private Difficulty difficulty;

    private Set<Category> categories = new HashSet<>();

    public String getId() {
        return id;
    }

    public Recipe setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public Recipe setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public Recipe setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public Integer getServings() {
        return servings;
    }

    public Recipe setServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Recipe setSource(String source) {
        this.source = source;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Recipe setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDirections() {
        return directions;
    }

    public Recipe setDirections(String directions) {
        this.directions = directions;
        return this;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Byte[] getImage() {
        return image;
    }

    public Recipe setImage(Byte[] image) {
        this.image = image;
        return this;
    }

    public Notes getNotes() {
        return notes;
    }

    public Recipe setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            //notes.setRecipe(this);
        }
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Recipe setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Recipe setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        //ingredient.setRecipe(this);
        ingredients.add(ingredient);
        return this;
    }

}
