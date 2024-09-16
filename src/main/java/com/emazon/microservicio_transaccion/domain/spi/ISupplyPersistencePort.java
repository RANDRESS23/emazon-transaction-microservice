package com.emazon.microservicio_transaccion.domain.spi;

import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;

public interface ISupplyPersistencePort {
    Supply saveSupply(Supply supply);
    Supply updateSupplyState(Long supplyId, State newState, String failureMessage);
}
