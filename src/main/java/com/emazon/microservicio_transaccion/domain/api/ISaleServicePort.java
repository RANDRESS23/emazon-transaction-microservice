package com.emazon.microservicio_transaccion.domain.api;

import com.emazon.microservicio_transaccion.domain.model.Sale;

public interface ISaleServicePort {
    void saveSale(Sale sale);
}
