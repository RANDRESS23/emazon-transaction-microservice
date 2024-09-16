package com.emazon.microservicio_transaccion.adapters.driving.mapper;

import com.emazon.microservicio_transaccion.adapters.driving.dto.response.SupplyResponse;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyResponseMapper {
    SupplyResponse toSupplyResponse(Supply supply);
}
