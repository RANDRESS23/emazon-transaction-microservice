package com.emazon.microservicio_transaccion.adapters.driving.controller;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSaleRequest;
import com.emazon.microservicio_transaccion.adapters.driving.dto.request.ProductSoldDto;
import com.emazon.microservicio_transaccion.adapters.driving.mapper.ISaleRequestMapper;
import com.emazon.microservicio_transaccion.domain.api.ISaleServicePort;
import com.emazon.microservicio_transaccion.domain.model.ProductSold;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleRestControllerTest {
    @Mock
    private ISaleServicePort saleServicePort;

    @Mock
    private ISaleRequestMapper saleRequestMapper;

    @InjectMocks
    private SaleRestController saleRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSale_shouldReturnCreatedStatusAndSaleId() {
        // Crear el request
        AddSaleRequest request = createMockAddSaleRequest();

        // Crear un objeto Sale simulado que será retornado por el mapper
        Sale sale = createMockSale();

        // Simular el comportamiento del mapper y del servicio
        when(saleRequestMapper.addRequestToSale(request)).thenReturn(sale);
        when(saleServicePort.saveSale(sale)).thenReturn(1L);

        // Ejecutar el método del controlador
        ResponseEntity<Long> response = saleRestController.addSale(request);

        // Verificar que el servicio fue llamado con la venta correcta
        verify(saleServicePort, times(1)).saveSale(sale);

        // Verificar el estado HTTP y el ID de la venta retornado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

    @Test
    void deleteSale_shouldReturnOkStatus() {
        // Simular el comportamiento del servicio de eliminación de ventas
        doNothing().when(saleServicePort).deleteSale(1L);

        // Ejecutar el método del controlador
        ResponseEntity<HttpStatus> response = saleRestController.deleteSale(1L);

        // Verificar que el servicio fue llamado correctamente
        verify(saleServicePort, times(1)).deleteSale(1L);

        // Verificar el estado HTTP retornado
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private AddSaleRequest createMockAddSaleRequest() {
        return new AddSaleRequest(
                1L,
                "client@example.com",
                10L,
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                Arrays.asList(
                        new ProductSoldDto(1L, "Product 1", 2L, new BigDecimal("10.00"), new BigDecimal("20.00")),
                        new ProductSoldDto(2L, "Product 2", 3L, new BigDecimal("15.00"), new BigDecimal("45.00"))
                )
        );
    }

    private Sale createMockSale() {
        return new Sale(
                1L,
                1L,
                "client@example.com",
                10L,
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                Arrays.asList(
                        new ProductSold(1L, 1L, 1L, "Product 1", 2L, new BigDecimal("10.00"), new BigDecimal("20.00")),
                        new ProductSold(2L, 2L, 2L, "Product 2", 3L, new BigDecimal("15.00"), new BigDecimal("45.00"))
                )
        );
    }
}