package com.emazon.microservicio_transaccion.adapters.driving.dto.response;

import com.emazon.microservicio_transaccion.domain.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class SupplyResponse {
    private final Long supplyId;
    private final Long productId;
    private final Long extraQuantity;
    private final Long auxBodegaId;
    private final LocalDate date;
    private final LocalTime hour;
    private State state;
    private final String failureReason;
}
