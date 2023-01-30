package guru.springframeword.sfgrecipes.repositories;

import guru.springframeword.sfgrecipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
