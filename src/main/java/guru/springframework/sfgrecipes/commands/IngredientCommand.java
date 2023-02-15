package guru.springframework.sfgrecipes.commands;

import java.math.BigDecimal;

public class IngredientCommand {
    private String id;
    private String recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

    public String getId() {
        return id;
    }

    public IngredientCommand setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IngredientCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public IngredientCommand setRecipeId(String recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public IngredientCommand setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public UnitOfMeasureCommand getUom() {
        return uom;
    }

    public IngredientCommand setUom(UnitOfMeasureCommand uom) {
        this.uom = uom;
        return this;
    }
}