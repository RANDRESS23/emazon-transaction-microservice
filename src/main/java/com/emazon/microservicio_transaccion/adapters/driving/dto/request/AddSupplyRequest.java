package com.emazon.microservicio_transaccion.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddSupplyRequest {
    private final Long productId;
    private final Long quantity;
    private final Boolean isAddProductQuantity;
}
