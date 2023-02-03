package guru.springframeword.sfgrecipes.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Long getId() {
        return id;
    }

    public Ingredient setId(Long id) {
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
