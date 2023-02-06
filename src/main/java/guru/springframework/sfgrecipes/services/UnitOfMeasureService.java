package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
