package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SaleEntity;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleEntityMapper {
    SaleEntity toEntity(Sale sale);
    Sale toDomainModel(SaleEntity saleEntity);
}
