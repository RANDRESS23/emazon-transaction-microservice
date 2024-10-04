package com.emazon.microservicio_transaccion.adapters.driving.mapper;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSaleRequest;
import com.emazon.microservicio_transaccion.adapters.driving.dto.request.ProductSoldDto;
import com.emazon.microservicio_transaccion.domain.model.ProductSold;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISaleRequestMapper {
    ProductSold toProductSold(ProductSoldDto productSoldDto);

    default Sale addRequestToSale(AddSaleRequest addSaleRequest) {
        List<ProductSold> listOfProductsSold = addSaleRequest.getProducts().stream()
            .map(this::toProductSold)
            .toList();

        return new Sale(
            null,
            addSaleRequest.getClientId(),
            addSaleRequest.getEmail(),
            addSaleRequest.getTotalQuantity(),
            addSaleRequest.getTotalPrice(),
            addSaleRequest.getDate(),
            listOfProductsSold
        );
    }
}
