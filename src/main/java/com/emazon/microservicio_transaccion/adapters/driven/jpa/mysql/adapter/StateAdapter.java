package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.IStateEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.IStateRepository;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.spi.IStatePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StateAdapter implements IStatePersistencePort {
    private final IStateRepository stateRepository;
    private final IStateEntityMapper stateEntityMapper;

    @Override
    public Optional<State> getStateByName(StateEnum name) {
        return stateRepository.findByName(name)
                .map(stateEntityMapper::toDomainModel);
    }
}
