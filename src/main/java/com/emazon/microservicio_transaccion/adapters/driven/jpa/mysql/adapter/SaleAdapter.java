package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SaleEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.ISaleEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.ISaleRepository;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import com.emazon.microservicio_transaccion.domain.spi.ISalePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class SaleAdapter implements ISalePersistencePort {
    private final ISaleRepository saleRepository;
    private final ISaleEntityMapper saleEntityMapper;

    @Transactional
    @Override
    public Sale saveSale(Sale sale) {
        SaleEntity saleEntity = saleRepository.save(saleEntityMapper.toEntity(sale));
        return saleEntityMapper.toDomainModel(saleEntity);
    }

    @Transactional
    @Override
    public void deleteSale(Long saleId) {
        saleRepository.removeSale(saleId);
    }
}
