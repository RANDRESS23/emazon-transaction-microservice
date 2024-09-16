package com.emazon.microservicio_transaccion.adapters.driving.controller;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSupplyRequest;
import com.emazon.microservicio_transaccion.adapters.driving.dto.response.SupplyResponse;
import com.emazon.microservicio_transaccion.adapters.driving.mapper.ISupplyResponseMapper;
import com.emazon.microservicio_transaccion.domain.api.ISupplyServicePort;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplyRestControllerTest {
    @Mock
    private ISupplyServicePort supplyServicePort;

    @Mock
    private ISupplyResponseMapper supplyResponseMapper;

    @InjectMocks
    private SupplyRestController supplyRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSupply_shouldReturnCreatedSupplyResponse() {
        // Arrange
        AddSupplyRequest addSupplyRequest = new AddSupplyRequest(
                1L,
                50L
        );
        State state = new State(1L, StateEnum.APROBADO);
        Supply supply = new Supply.SupplyBuilder()
                .supplyId(1L)
                .auxBodegaId(2L)
                .productId(1L)
                .extraQuantity(50L)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .state(state)
                .build();

        SupplyResponse expectedResponse = new SupplyResponse(
                1L,
                1L,
                50L,
                2L,
                LocalDate.now(),
                LocalTime.now(),
                state,
                null
        );

        when(supplyServicePort.saveSupply(supply)).thenReturn(supply);
        when(supplyResponseMapper.toSupplyResponse(supply)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<SupplyResponse> response = supplyRestController.addSupply(addSupplyRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}