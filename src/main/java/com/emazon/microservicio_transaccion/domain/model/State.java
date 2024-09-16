package com.emazon.microservicio_transaccion.domain.model;

import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class State {
    private final Long stateId;
    private final StateEnum name;

    public State(Long stateId, StateEnum name) {
        this.stateId = stateId;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
    }

    public Long getStateId() {
        return stateId;
    }

    public StateEnum getName() {
        return name;
    }
}
