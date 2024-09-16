package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.StateEntity;
import com.emazon.microservicio_transaccion.domain.model.State;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStateEntityMapper {
    StateEntity toEntity(State state);
    State toDomainModel(StateEntity stateEntity);
}
