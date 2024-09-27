package com.emazon.microservicio_transaccion.domain.spi;

import com.emazon.microservicio_transaccion.domain.model.ProductSold;

public interface IProductSoldPersistencePort {
    void saveProductSold(ProductSold productSold);
}
