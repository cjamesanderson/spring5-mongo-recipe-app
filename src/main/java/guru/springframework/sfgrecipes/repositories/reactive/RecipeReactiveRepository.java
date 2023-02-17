package guru.springframework.sfgrecipes.repositories.reactive;

import guru.springframework.sfgrecipes.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
