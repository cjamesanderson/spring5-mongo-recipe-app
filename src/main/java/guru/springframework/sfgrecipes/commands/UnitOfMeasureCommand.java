package guru.springframework.sfgrecipes.commands;

public class UnitOfMeasureCommand {
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public UnitOfMeasureCommand setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UnitOfMeasureCommand setDescription(String description) {
        this.description = description;
        return this;
    }
}
