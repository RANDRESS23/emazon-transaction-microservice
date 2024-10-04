package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.ProductSoldEntity;
import com.emazon.microservicio_transaccion.domain.model.ProductSold;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductSoldEntityMapper {
    ProductSoldEntity toEntity(ProductSold productSold);
    ProductSold toDomainModel(ProductSoldEntity productSoldEntity);
}
