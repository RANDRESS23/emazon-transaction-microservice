package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import com.emazon.microservicio_transaccion.domain.spi.IAuthPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.IStatePersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.IStockPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.ISupplyPersistencePort;
import com.emazon.microservicio_transaccion.domain.validation.ValidationFailureMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplyUseCaseTest {
    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IStatePersistencePort statePersistencePort;

    @Mock
    private IAuthPersistencePort authPersistencePort;

    @Mock
    private IStockPersistencePort stockPersistencePort;

    @Mock
    private ValidationFailureMessage validationFailureMessage;

    @InjectMocks
    private SupplyUseCase supplyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSupply_shouldSaveAndApproveSupplyWhenStockUpdateSucceeds() {
        // Arrange
        Supply supply = new Supply.SupplyBuilder()
                .supplyId(1L)
                .productId(1L)
                .extraQuantity(10L)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();

        State pendingState = new State(1L, StateEnum.PENDIENTE);
        State approvedState = new State(2L, StateEnum.APROBADO);
        Long auxBodegaId = 100L;

        when(statePersistencePort.getStateByName(StateEnum.PENDIENTE)).thenReturn(Optional.of(pendingState));
        when(statePersistencePort.getStateByName(StateEnum.APROBADO)).thenReturn(Optional.of(approvedState));
        when(authPersistencePort.getAuthenticatedUserId()).thenReturn(auxBodegaId);
        when(supplyPersistencePort.saveSupply(supply)).thenReturn(supply);
        when(supplyPersistencePort.updateSupplyState(supply.getSupplyId(), approvedState, null)).thenReturn(supply);

        // Act
        Supply result = supplyUseCase.saveSupply(supply);

        // Assert
        assertEquals(supply, result);
        verify(stockPersistencePort, times(1)).updateProductQuantity(supply.getProductId(), supply.getExtraQuantity(), true);
        verify(supplyPersistencePort, times(1)).updateSupplyState(supply.getSupplyId(), approvedState, null);
    }

    @Test
    void saveSupply_shouldRejectSupplyWhenStockUpdateFails() {
        // Arrange
        Supply supply = new Supply.SupplyBuilder()
                .supplyId(1L)
                .productId(1L)
                .extraQuantity(10L)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();

        State pendingState = new State(1L, StateEnum.PENDIENTE);
        State rejectState = new State(3L, StateEnum.RECHAZADO);
        Long auxBodegaId = 100L;

        String failureMessage = "Stock update failed";

        when(statePersistencePort.getStateByName(StateEnum.PENDIENTE)).thenReturn(Optional.of(pendingState));
        when(statePersistencePort.getStateByName(StateEnum.RECHAZADO)).thenReturn(Optional.of(rejectState));
        when(authPersistencePort.getAuthenticatedUserId()).thenReturn(auxBodegaId);
        when(supplyPersistencePort.saveSupply(supply)).thenReturn(supply);
        doThrow(new RuntimeException("Stock update failed")).when(stockPersistencePort).updateProductQuantity(supply.getProductId(), supply.getExtraQuantity(), true);
        when(validationFailureMessage.parseFailureMessage(anyString())).thenReturn(failureMessage);
        when(supplyPersistencePort.updateSupplyState(supply.getSupplyId(), rejectState, failureMessage)).thenReturn(supply);

        // Act
        Supply result = supplyUseCase.saveSupply(supply);

        // Assert
        assertEquals(supply, result);
        verify(stockPersistencePort, times(1)).updateProductQuantity(supply.getProductId(), supply.getExtraQuantity(), true);
        verify(supplyPersistencePort, times(1)).updateSupplyState(supply.getSupplyId(), rejectState, failureMessage);
    }
}