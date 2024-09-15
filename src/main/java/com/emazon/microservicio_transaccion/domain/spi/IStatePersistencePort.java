package com.emazon.microservicio_transaccion.domain.spi;

import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;

import java.util.Optional;

public interface IStatePersistencePort {
    Optional<State> getStateByName(StateEnum name);
}
