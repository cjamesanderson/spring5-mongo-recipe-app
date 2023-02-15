package guru.springframework.sfgrecipes.domain;

public class UnitOfMeasure {

    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public UnitOfMeasure setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnitOfMeasure setDescription(String description) {
        this.description = description;
        return this;
    }
}
