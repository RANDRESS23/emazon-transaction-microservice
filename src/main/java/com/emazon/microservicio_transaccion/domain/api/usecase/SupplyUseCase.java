package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.api.IStateServicePort;
import com.emazon.microservicio_transaccion.domain.validation.ValidationFailureMessage;
import com.emazon.microservicio_transaccion.domain.api.ISupplyServicePort;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import com.emazon.microservicio_transaccion.domain.model.State;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import com.emazon.microservicio_transaccion.domain.spi.IAuthPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.IStockPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.ISupplyPersistencePort;

public class SupplyUseCase implements ISupplyServicePort {
    private final ISupplyPersistencePort supplyPersistencePort;
    private final IStateServicePort stateServicePort;
    private final IAuthPersistencePort authPersistencePort;
    private final IStockPersistencePort stockPersistencePort;
    private final ValidationFailureMessage validationFailureMessage;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort, IStateServicePort stateServicePort, IAuthPersistencePort authPersistencePort, IStockPersistencePort stockPersistencePort, ValidationFailureMessage validationFailureMessage) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.stateServicePort = stateServicePort;
        this.authPersistencePort = authPersistencePort;
        this.stockPersistencePort = stockPersistencePort;
        this.validationFailureMessage = validationFailureMessage;
    }

    @Override
    public Supply saveSupply(Supply supply) {
        State pendingState = stateServicePort.getState(StateEnum.PENDIENTE);
        State approvedState = stateServicePort.getState(StateEnum.APROBADO);
        State rejectState = stateServicePort.getState(StateEnum.RECHAZADO);
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

    @Override
    public Supply getLastSupplyByProductId(Long productId) {
        return supplyPersistencePort.getLastSupplyByProductId(productId).orElse(null);
    }
}
