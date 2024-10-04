package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.model.ProductSold;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import com.emazon.microservicio_transaccion.domain.spi.IProductSoldPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.ISalePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleUseCaseTest {
    @Mock
    private ISalePersistencePort salePersistencePort;

    @Mock
    private IProductSoldPersistencePort productSoldPersistencePort;

    @InjectMocks
    private SaleUseCase saleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSale_shouldSaveSaleAndProducts() {
        // Crear una venta simulada y una venta guardada simulada
        Sale sale = createMockSale();
        Sale saleSaved = createMockSale();

        // Simular comportamiento del puerto de persistencia de la venta
        when(salePersistencePort.saveSale(sale)).thenReturn(saleSaved);

        // Ejecutar el método del caso de uso
        Long resultSaleId = saleUseCase.saveSale(sale);

        // Verificar que se haya guardado la venta
        verify(salePersistencePort, times(1)).saveSale(sale);

        // Verificar que se hayan guardado los productos vendidos con el ID de venta correcto
        for (ProductSold productSold : sale.getProducts()) {
            productSold.setSaleId(saleSaved.getSaleId());
            verify(productSoldPersistencePort, times(1)).saveProductSold(productSold);
        }

        // Verificar que el ID devuelto sea el esperado
        assertEquals(saleSaved.getSaleId(), resultSaleId);
    }

    @Test
    void deleteSale_shouldDeleteSaleAndProducts() {
        Long saleId = 1L;

        // Simular comportamiento del puerto de persistencia
        doNothing().when(salePersistencePort).deleteSale(saleId);
        doNothing().when(productSoldPersistencePort).removeAllCartProductsSold(saleId);

        // Ejecutar el método del caso de uso
        saleUseCase.deleteSale(saleId);

        // Verificar que se haya eliminado la venta
        verify(salePersistencePort, times(1)).deleteSale(saleId);

        // Verificar que se hayan eliminado los productos vendidos asociados
        verify(productSoldPersistencePort, times(1)).removeAllCartProductsSold(saleId);
    }


    private Sale createMockSale() {
        List<ProductSold> products = Arrays.asList(
                new ProductSold(1L, 1L, 1L, "Product 1", 2L, new BigDecimal("10.00"), new BigDecimal("20.00")),
                new ProductSold(2L, 1L, 2L, "Product 2", 3L, new BigDecimal("15.00"), new BigDecimal("45.00"))
        );
        return new Sale(1L, 1L, "client@example.com", 5L, new BigDecimal("65.00"), null, products);
    }
}