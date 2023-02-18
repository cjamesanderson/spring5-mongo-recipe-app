package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
    Flux<UnitOfMeasureCommand> listAllUoms();
}
