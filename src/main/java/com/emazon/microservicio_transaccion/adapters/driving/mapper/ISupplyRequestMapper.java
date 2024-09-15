package com.emazon.microservicio_transaccion.adapters.driving.mapper;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSupplyRequest;
import com.emazon.microservicio_transaccion.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_transaccion.domain.model.Supply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Mapper(componentModel = "spring")
public interface ISupplyRequestMapper {
    @Mapping(target = DrivingConstants.SUPPLY_ID, ignore = true)
    static Supply addRequestToSupply(AddSupplyRequest addSupplyRequest) {
        return new Supply.SupplyBuilder()
                .productId(addSupplyRequest.getProductId())
                .extraQuantity(addSupplyRequest.getExtraQuantity())
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
    }
}
