package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSupplyRequest;
import com.emazon.microservicio_transaccion.configuration.feign.IStockFeignClient;
import com.emazon.microservicio_transaccion.domain.spi.IStockPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockAdapter implements IStockPersistencePort {
    private final IStockFeignClient stockFeignClient;

    @Override
    public void updateProductQuantity(Long productId, Long quantity, boolean isAddProductQuantity) {
        AddSupplyRequest request = new AddSupplyRequest(productId, quantity, isAddProductQuantity);

        stockFeignClient.updateProductQuantity(request);
    }
}
