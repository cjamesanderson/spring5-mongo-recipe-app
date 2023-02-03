package guru.springframework.sfgrecipes.commands;

public class UnitOfMeasureCommand {
    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public UnitOfMeasureCommand setId(Long id) {
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
