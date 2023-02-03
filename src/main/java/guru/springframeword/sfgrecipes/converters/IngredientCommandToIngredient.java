package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.IngredientCommand;
import guru.springframeword.sfgrecipes.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) return null;

        //todo: implement recipe parameter conversion
        return new Ingredient()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setAmount(source.getAmount())
                .setUom(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUom()));
    }
}
