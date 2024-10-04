package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.ProductSoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductSoldRepository extends JpaRepository<ProductSoldEntity, Long> {
    @Modifying
    @Query(value = "DELETE FROM products_sold WHERE sale_id = :saleId", nativeQuery = true)
    void removeAllCartProductsSold(@Param("saleId") Long saleId);
}
