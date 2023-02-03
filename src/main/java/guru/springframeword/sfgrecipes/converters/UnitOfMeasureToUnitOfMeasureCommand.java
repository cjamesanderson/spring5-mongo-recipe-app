package guru.springframeword.sfgrecipes.converters;

import guru.springframeword.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframeword.sfgrecipes.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) return null;

        final UnitOfMeasureCommand command = new UnitOfMeasureCommand()
                .setId(source.getId())
                .setDescription(source.getDescription());
        return command;
    }
}
