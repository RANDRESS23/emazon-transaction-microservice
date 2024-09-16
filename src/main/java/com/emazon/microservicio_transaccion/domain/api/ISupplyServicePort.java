package com.emazon.microservicio_transaccion.domain.api;

import com.emazon.microservicio_transaccion.domain.model.Supply;

public interface ISupplyServicePort {
    Supply saveSupply(Supply supply);
}
