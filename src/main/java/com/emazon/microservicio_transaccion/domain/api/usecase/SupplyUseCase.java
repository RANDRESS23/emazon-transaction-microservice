package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.validation.ValidationFailureMessage;
import com.emazon.microservicio_transaccion.domain.api.ISupplyServicePort;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import com.emazon.microservicio_transaccion.domain.spi.IAuthPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.IStatePersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.IStockPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.ISupplyPersistencePort;

public class SupplyUseCase implements ISupplyServicePort {
    private final ISupplyPersistencePort supplyPersistencePort;
    private final IStatePersistencePort statePersistencePort;
    private final IAuthPersistencePort authPersistencePort;
    private final IStockPersistencePort stockPersistencePort;
    private final ValidationFailureMessage validationFailureMessage;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort, IStatePersistencePort statePersistencePort, IAuthPersistencePort authPersistencePort, IStockPersistencePort stockPersistencePort, ValidationFailureMessage validationFailureMessage) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.statePersistencePort = statePersistencePort;
        this.authPersistencePort = authPersistencePort;
        this.stockPersistencePort = stockPersistencePort;
        this.validationFailureMessage = validationFailureMessage;
    }

    @Override
    public Supply saveSupply(Supply supply) {
        State pendingState = statePersistencePort.getStateByName(StateEnum.PENDIENTE).orElse(new State(1L, StateEnum.PENDIENTE));
        State approvedState = statePersistencePort.getStateByName(StateEnum.APROBADO).orElse(new State(2L, StateEnum.APROBADO));
        State rejectState = statePersistencePort.getStateByName(StateEnum.RECHAZADO).orElse(new State(3L, StateEnum.RECHAZADO));
        Long auxBodegaId = authPersistencePort.getAuthenticatedUserId();

        supply.setAuxBodegaId(auxBodegaId);
        supply.setState(pendingState);

        Supply supplySaved = supplyPersistencePort.saveSupply(supply);

        try {
            stockPersistencePort.updateProductQuantity(supplySaved.getProductId(), supplySaved.getExtraQuantity());
            return supplyPersistencePort.updateSupplyState(supplySaved.getSupplyId(), approvedState, null);
        } catch (Exception e) {
            String failureMessage = validationFailureMessage.parseFailureMessage(e.getMessage());
            return supplyPersistencePort.updateSupplyState(supplySaved.getSupplyId(), rejectState, failureMessage);
        }
    }
}
