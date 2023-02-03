package guru.springframework.sfgrecipes.repositories;

import guru.springframework.sfgrecipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
