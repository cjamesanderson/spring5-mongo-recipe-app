package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {
        //given
        Set<UnitOfMeasure> uomSet = new HashSet<>();
        uomSet.add(new UnitOfMeasure().setId("1"));
        uomSet.add(new UnitOfMeasure().setId("2"));
        uomSet.add(new UnitOfMeasure().setId("3"));
        when(unitOfMeasureRepository.findAll()).thenReturn(uomSet);

        //when
        Set<UnitOfMeasureCommand> commandSet = service.listAllUoms();

        //then
        assertNotNull(commandSet);
        assertEquals(uomSet.size(), commandSet.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}