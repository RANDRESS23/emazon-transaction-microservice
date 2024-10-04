package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
    @Modifying
    @Query(value = "DELETE FROM sale WHERE sale_id = :saleId", nativeQuery = true)
    void removeSale(@Param("saleId") Long saleId);
}
