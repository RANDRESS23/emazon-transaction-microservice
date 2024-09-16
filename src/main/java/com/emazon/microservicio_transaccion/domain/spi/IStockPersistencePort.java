package com.emazon.microservicio_transaccion.domain.spi;

public interface IStockPersistencePort {
    void updateProductQuantity(Long productId, Long extraQuantity);
}
