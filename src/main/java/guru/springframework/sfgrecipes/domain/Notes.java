package guru.springframework.sfgrecipes.domain;


import org.springframework.data.annotation.Id;

public class Notes {

    @Id
    private String id;

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

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public Notes setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
        return this;
    }
}
