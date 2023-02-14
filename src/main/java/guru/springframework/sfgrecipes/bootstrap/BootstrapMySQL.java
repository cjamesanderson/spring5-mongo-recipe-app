package guru.springframework.sfgrecipes.bootstrap;

import guru.springframework.sfgrecipes.domain.Category;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.repositories.CategoryRepository;
import guru.springframework.sfgrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (categoryRepository.count() == 0L) {
            System.out.println("LOGGING: Loading Categories");
            loadCategories();
        }

        if (unitOfMeasureRepository.count() == 0L) {
            System.out.println("LOGGING: Loading UOMs");
            loadUom();
        }
    }

    private void loadCategories() {
        categoryRepository.save(new Category().setDescription("American"));
        categoryRepository.save(new Category().setDescription("Italian"));
        categoryRepository.save(new Category().setDescription("Mexican"));
        categoryRepository.save(new Category().setDescription("Fast Food"));
    }

    private void loadUom() {
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Teaspoon"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Tablespoon"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Cup"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Pinch"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Ounce"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Clove"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Each"));
        unitOfMeasureRepository.save(new UnitOfMeasure().setDescription("Pint"));
    }
}
