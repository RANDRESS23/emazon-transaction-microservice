package com.emazon.microservicio_transaccion.configuration;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.adapter.*;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.IProductSoldEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.ISaleEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.IStateEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.mapper.ISupplyEntityMapper;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.IProductSoldRepository;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.ISaleRepository;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.IStateRepository;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.ISupplyRepository;
import com.emazon.microservicio_transaccion.domain.api.ISaleServicePort;
import com.emazon.microservicio_transaccion.domain.api.usecase.SaleUseCase;
import com.emazon.microservicio_transaccion.domain.spi.*;
import com.emazon.microservicio_transaccion.domain.validation.ValidationFailureMessage;
import com.emazon.microservicio_transaccion.configuration.feign.IStockFeignClient;
import com.emazon.microservicio_transaccion.configuration.securityconfig.jwtconfiguration.JwtService;
import com.emazon.microservicio_transaccion.domain.api.IStateServicePort;
import com.emazon.microservicio_transaccion.domain.api.ISupplyServicePort;
import com.emazon.microservicio_transaccion.domain.api.usecase.StateUseCase;
import com.emazon.microservicio_transaccion.domain.api.usecase.SupplyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;
    private final IStateRepository stateRepository;
    private final ISaleRepository saleRepository;
    private final IProductSoldRepository productSoldRepository;
    private final IStateEntityMapper stateEntityMapper;
    private final ISaleEntityMapper saleEntityMapper;
    private final IProductSoldEntityMapper productSoldEntityMapper;
    private final JwtService jwtService;
    private final IStockFeignClient stockFeignClient;

    @Bean
    public ISupplyPersistencePort supplyPersistencePort() {
        return new SupplyAdapter(supplyRepository, supplyEntityMapper, stateEntityMapper);
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthAdapter(jwtService);
    }

    @Bean
    public IStockPersistencePort stockPersistencePort() {
        return new StockAdapter(stockFeignClient);
    }

    @Bean
    public ValidationFailureMessage validationFailureMessage() {
        return new ValidationFailureMessage();
    }

    @Bean
    public ISupplyServicePort supplyServicePort() {
        return new SupplyUseCase(supplyPersistencePort(), stateServicePort(), authPersistencePort(), stockPersistencePort(), validationFailureMessage());
    }

    @Bean
    public IStatePersistencePort statePersistencePort() {
        return new StateAdapter(stateRepository, stateEntityMapper);
    }

    @Bean
    public IStateServicePort stateServicePort() {
        return new StateUseCase(statePersistencePort());
    }

    @Bean
    public ISalePersistencePort salePersistencePort() {
        return new SaleAdapter(saleRepository, saleEntityMapper);
    }

    @Bean
    public IProductSoldPersistencePort productSoldPersistencePort() {
        return new ProductSoldAdapter(productSoldRepository, productSoldEntityMapper);
    }

    @Bean
    public ISaleServicePort saleServicePort() {
        return new SaleUseCase(salePersistencePort(), productSoldPersistencePort());
    }
}
