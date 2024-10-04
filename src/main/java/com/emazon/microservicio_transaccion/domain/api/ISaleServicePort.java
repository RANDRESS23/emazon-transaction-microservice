package com.emazon.microservicio_transaccion.domain.api;

import com.emazon.microservicio_transaccion.domain.model.Sale;

public interface ISaleServicePort {
    Long saveSale(Sale sale);
    void deleteSale(Long saleId);
}
