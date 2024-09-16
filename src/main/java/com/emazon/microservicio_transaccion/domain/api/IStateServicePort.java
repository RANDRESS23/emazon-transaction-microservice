package com.emazon.microservicio_transaccion.domain.api;

import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;

public interface IStateServicePort {
    State getState(StateEnum name);
}
