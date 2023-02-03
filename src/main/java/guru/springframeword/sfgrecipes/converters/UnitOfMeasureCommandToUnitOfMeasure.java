package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframeword.sfgrecipes.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure()
                .setId(source.getId())
                .setDescription(source.getDescription());
        return uom;
    }
}
