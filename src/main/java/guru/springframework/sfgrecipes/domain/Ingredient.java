package guru.springframework.sfgrecipes.domain;

import java.math.BigDecimal;

public class Ingredient {

    private String id;
    private String description;
    private BigDecimal amount;
    private Recipe recipe;
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public String getId() {
        return id;
    }

    public Ingredient setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Ingredient setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Ingredient setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Ingredient setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public Ingredient setUom(UnitOfMeasure uom) {
        this.uom = uom;
        return this;
    }
}
