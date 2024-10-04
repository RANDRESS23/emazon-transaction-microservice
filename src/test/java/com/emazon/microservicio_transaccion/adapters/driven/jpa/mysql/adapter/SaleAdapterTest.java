package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SaleEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.ISaleEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.ISaleRepository;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleAdapterTest {
    @Mock
    private ISaleRepository saleRepository;

    @Mock
    private ISaleEntityMapper saleEntityMapper;

    @InjectMocks
    private SaleAdapter saleAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSale_shouldReturnSavedSale() {
        // Crear un objeto Sale y SaleEntity simulados
        Sale sale = createMockSale();
        SaleEntity saleEntity = createMockSaleEntity();

        // Simular el comportamiento del mapper y del repositorio
        when(saleEntityMapper.toEntity(sale)).thenReturn(saleEntity);
        when(saleRepository.save(saleEntity)).thenReturn(saleEntity);
        when(saleEntityMapper.toDomainModel(saleEntity)).thenReturn(sale);

        // Ejecutar el método del adaptador
        Sale result = saleAdapter.saveSale(sale);

        // Verificar que el repositorio y los mappers fueron llamados correctamente
        verify(saleEntityMapper, times(1)).toEntity(sale);
        verify(saleRepository, times(1)).save(saleEntity);
        verify(saleEntityMapper, times(1)).toDomainModel(saleEntity);

        // Verificar que la venta devuelta es la esperada
        assertEquals(sale, result);
    }

    @Test
    void deleteSale_shouldInvokeRepositoryDelete() {
        // Simular el comportamiento del repositorio
        doNothing().when(saleRepository).removeSale(1L);

        // Ejecutar el método del adaptador
        saleAdapter.deleteSale(1L);

        // Verificar que el repositorio fue llamado correctamente
        verify(saleRepository, times(1)).removeSale(1L);
    }

    private Sale createMockSale() {
        return new Sale(
                1L,
                1L,
                "client@example.com",
                10L,
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                null // Productos pueden ser nulos o incluir una lista simulada de productos vendidos
        );
    }

    private SaleEntity createMockSaleEntity() {
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setSaleId(1L);
        saleEntity.setClientId(1L);
        saleEntity.setEmail("client@example.com");
        saleEntity.setTotalQuantity(10L);
        saleEntity.setTotalPrice(new BigDecimal("100.00"));
        saleEntity.setDate(LocalDateTime.now());
        return saleEntity;
    }
}