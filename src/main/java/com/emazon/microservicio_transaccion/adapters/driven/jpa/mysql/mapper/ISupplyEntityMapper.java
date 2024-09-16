package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SupplyEntity;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyEntityMapper {
    SupplyEntity toEntity(Supply supply);

    static Supply toDomainModel(SupplyEntity supplyEntity, IStateEntityMapper stateEntityMapper) {
        State state = stateEntityMapper.toDomainModel(supplyEntity.getState());

        return new Supply.SupplyBuilder()
                .supplyId(supplyEntity.getSupplyId())
                .productId(supplyEntity.getProductId())
                .extraQuantity(supplyEntity.getExtraQuantity())
                .auxBodegaId(supplyEntity.getAuxBodegaId())
                .date(supplyEntity.getDate())
                .hour(supplyEntity.getHour())
                .state(state)
                .failureReason(supplyEntity.getFailureReason())
                .build();
    }
}
