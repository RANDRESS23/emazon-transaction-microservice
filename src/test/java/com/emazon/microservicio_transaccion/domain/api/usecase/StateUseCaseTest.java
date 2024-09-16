package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.exception.NotFoundException;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.spi.IStatePersistencePort;
import com.emazon.microservicio_transaccion.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StateUseCaseTest {
    @Mock
    private IStatePersistencePort statePersistencePort;

    @InjectMocks
    private StateUseCase stateUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getState_shouldReturnStateWhenFound() {
        // Arrange
        StateEnum stateEnum = StateEnum.APROBADO;
        State expectedState = new State(1L, StateEnum.APROBADO);

        when(statePersistencePort.getStateByName(stateEnum)).thenReturn(Optional.of(expectedState));

        // Act
        State result = stateUseCase.getState(stateEnum);

        // Assert
        assertEquals(expectedState, result);
    }

    @Test
    void getState_shouldThrowNotFoundExceptionWhenStateNotFound() {
        // Arrange
        StateEnum stateEnum = StateEnum.RECHAZADO;

        when(statePersistencePort.getStateByName(stateEnum)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> stateUseCase.getState(stateEnum), DomainConstants.STATE_NOT_FOUND);
    }
}