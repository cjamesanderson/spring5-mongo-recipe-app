package guru.springframework.sfgrecipes.repositories.reactive;

import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UnitOfMeasureReactiveRepositoryTest {

    private static final String EACH = "Each";

    @Autowired
    UnitOfMeasureReactiveRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll().block();
    }

    @Test
    void testSaveUom() {
        UnitOfMeasure uom = new UnitOfMeasure().setId("1").setDescription(EACH);
        repository.save(uom).block();
/*
        Flux<UnitOfMeasure> uoms =  repository.findAll();
        uoms.map(savedUom -> {
            System.out.println(savedUom.getDescription());
            return savedUom;
        }).blockFirst();
*/
        Long uomCount = repository.count().block();
        assertEquals(1, uomCount);
    }

    @Test
    void testFindByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure().setDescription(EACH);

        repository.save(uom).block();

        UnitOfMeasure savedUom = repository.findByDescription(EACH).block();

        assertNotNull(savedUom);
        assertNotNull(savedUom.getId());
    }
}