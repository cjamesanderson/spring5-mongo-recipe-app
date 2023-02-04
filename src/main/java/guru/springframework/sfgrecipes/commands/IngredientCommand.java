package guru.springframework.sfgrecipes.commands;

import java.math.BigDecimal;

public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

    public Long getId() {
        return id;
    }

    public IngredientCommand setId(Long id) {
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

    public Long getRecipeId() {
        return recipeId;
    }

    public IngredientCommand setRecipeId(Long recipeId) {
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