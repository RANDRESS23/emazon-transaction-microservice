package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.IProductSoldEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.IProductSoldRepository;
import com.emazon.microservicio_transaccion.domain.model.ProductSold;
import com.emazon.microservicio_transaccion.domain.spi.IProductSoldPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ProductSoldAdapter implements IProductSoldPersistencePort {
    private final IProductSoldRepository productSoldRepository;
    private final IProductSoldEntityMapper productSoldEntityMapper;

    @Transactional
    @Override
    public void saveProductSold(ProductSold productSold) {
        productSoldRepository.save(productSoldEntityMapper.toEntity(productSold));
    }
}
