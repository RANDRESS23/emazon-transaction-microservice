package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.StateEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SupplyEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.exception.NotFoundException;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.IStateEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.ISupplyEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.ISupplyRepository;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import com.emazon.microservicio_transaccion.domain.spi.ISupplyPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyAdapter implements ISupplyPersistencePort {
    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;
    private final IStateEntityMapper stateEntityMapper;

    @Override
    public Supply saveSupply(Supply supply) {
        SupplyEntity supplyEntity = supplyRepository.save(supplyEntityMapper.toEntity(supply));
        return ISupplyEntityMapper.toDomainModel(supplyEntity, stateEntityMapper);
    }

    @Override
    public Supply updateSupplyState(Long supplyId, State newState, String failureMessage) {
        SupplyEntity supplyEntity = supplyRepository.findById(supplyId)
                .orElseThrow(() -> new NotFoundException(DrivenConstants.SUPPLY_NOT_FOUND));

        StateEntity stateEntity = stateEntityMapper.toEntity(newState);
        supplyEntity.setState(stateEntity);
        supplyEntity.setFailureReason(failureMessage);

        SupplyEntity supplyEntityUpdated = supplyRepository.save(supplyEntity);
        return ISupplyEntityMapper.toDomainModel(supplyEntityUpdated, stateEntityMapper);
    }
}
