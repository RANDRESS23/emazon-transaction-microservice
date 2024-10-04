package com.emazon.microservicio_transaccion.domain.api.usecase;

import com.emazon.microservicio_transaccion.domain.api.ISaleServicePort;
import com.emazon.microservicio_transaccion.domain.model.ProductSold;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import com.emazon.microservicio_transaccion.domain.spi.IProductSoldPersistencePort;
import com.emazon.microservicio_transaccion.domain.spi.ISalePersistencePort;

import java.util.List;

public class SaleUseCase implements ISaleServicePort {
    private final ISalePersistencePort salePersistencePort;
    private final IProductSoldPersistencePort productSoldPersistencePort;

    public SaleUseCase(ISalePersistencePort salePersistencePort, IProductSoldPersistencePort productSoldPersistencePort) {
        this.salePersistencePort = salePersistencePort;
        this.productSoldPersistencePort = productSoldPersistencePort;
    }

    @Override
    public Long saveSale(Sale sale) {
        Sale saleSaved = salePersistencePort.saveSale(sale);
        List<ProductSold> productsSold = sale.getProducts();

        productsSold.forEach(productSold -> {
            productSold.setSaleId(saleSaved.getSaleId());
            productSoldPersistencePort.saveProductSold(productSold);
        });

        return saleSaved.getSaleId();
    }

    @Override
    public void deleteSale(Long saleId) {
        salePersistencePort.deleteSale(saleId);
        productSoldPersistencePort.removeAllCartProductsSold(saleId);
    }
}
