package com.emazon.microservicio_transaccion.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class SupplyDto {
    private final Long supplyId;
    private final Long productId;
    private final LocalDate date;
}
