package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.StateEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SupplyEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.exception.NotFoundException;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.IStateEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.ISupplyEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.ISupplyRepository;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
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

class SupplyAdapterTest {
    @Mock
    private ISupplyRepository supplyRepository;

    @Mock
    private ISupplyEntityMapper supplyEntityMapper;

    @Mock
    private IStateEntityMapper stateEntityMapper;

    @InjectMocks
    private SupplyAdapter supplyAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSupply_shouldReturnSavedSupply() {
        // Arrange
        LocalTime hour = LocalTime.now();
        Supply supply = new Supply.SupplyBuilder()
                .supplyId(1L)
                .productId(1L)
                .extraQuantity(10L)
                .date(LocalDate.now())
                .hour(hour)
                .build();
        SupplyEntity supplyEntity = new SupplyEntity(
                1L,
                1L,
                10L,
                2L,
                LocalDate.now(),
                hour,
                null,
                null
        );

        when(supplyEntityMapper.toEntity(supply)).thenReturn(supplyEntity);
        when(supplyRepository.save(supplyEntity)).thenReturn(supplyEntity);

        // Act
        Supply result = supplyAdapter.saveSupply(supply);

        // Assert
        assertEquals(supply.getSupplyId(), result.getSupplyId());
        verify(supplyRepository, times(1)).save(supplyEntity);
        verify(supplyEntityMapper, times(1)).toEntity(supply);
    }

    @Test
    void updateSupplyState_shouldReturnUpdatedSupplyWhenSupplyExists() {
        // Arrange
        Long supplyId = 1L;
        State newState = new State(1L, StateEnum.APROBADO);
        SupplyEntity supplyEntity = new SupplyEntity(
                1L,
                1L,
                50L,
                2L,
                LocalDate.now(),
                LocalTime.now(),
                stateEntityMapper.toEntity(newState),
                null
        );
        StateEntity stateEntity = new StateEntity(1L, StateEnum.APROBADO);
        Supply supply = new Supply.SupplyBuilder()
                .supplyId(1L)
                .productId(1L)
                .extraQuantity(10L)
                .auxBodegaId(2L)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .state(newState)
                .build();
        String failureMessage = "Error updating stock";

        when(supplyRepository.findById(supplyId)).thenReturn(Optional.of(supplyEntity));
        when(stateEntityMapper.toEntity(newState)).thenReturn(stateEntity);
        when(supplyRepository.save(supplyEntity)).thenReturn(supplyEntity);

        // Act
        Supply result = supplyAdapter.updateSupplyState(supplyId, newState, failureMessage);

        // Assert
        assertEquals(supply.getSupplyId(), result.getSupplyId());
        verify(supplyRepository, times(1)).findById(supplyId);
        verify(stateEntityMapper, times(2)).toEntity(newState);
        verify(supplyRepository, times(1)).save(supplyEntity);
    }

    @Test
    void updateSupplyState_shouldThrowNotFoundExceptionWhenSupplyDoesNotExist() {
        // Arrange
        Long supplyId = 1L;
        State newState = new State(1L, StateEnum.APROBADO);
        String failureMessage = "Error updating stock";

        when(supplyRepository.findById(supplyId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> supplyAdapter.updateSupplyState(supplyId, newState, failureMessage));
        verify(supplyRepository, times(1)).findById(supplyId);
        verify(supplyRepository, never()).save(any(SupplyEntity.class));
    }

}