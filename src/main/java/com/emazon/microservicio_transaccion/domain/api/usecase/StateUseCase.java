package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.api.IStateServicePort;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.exception.NotFoundException;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.spi.IStatePersistencePort;
import com.emazon.microservicio_transaccion.domain.util.DomainConstants;

public class StateUseCase implements IStateServicePort {
    private final IStatePersistencePort statePersistencePort;

    public StateUseCase(IStatePersistencePort statePersistencePort) {
        this.statePersistencePort = statePersistencePort;
    }

    @Override
    public State getState(StateEnum name) {
        return statePersistencePort.getStateByName(name)
                .orElseThrow(() -> new NotFoundException(DomainConstants.STATE_NOT_FOUND));
    }
}
