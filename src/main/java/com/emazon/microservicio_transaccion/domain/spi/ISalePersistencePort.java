package com.emazon.microservicio_transaccion.domain.spi;

import com.emazon.microservicio_transaccion.domain.model.Sale;

public interface ISalePersistencePort {
    Sale saveSale(Sale sale);
}
