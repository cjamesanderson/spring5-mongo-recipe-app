package guru.springframeword.sfgrecipes.repositories;

import guru.springframeword.sfgrecipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
