package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SupplyEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util.DrivenConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {
    @Query(value = "SELECT * FROM supply s WHERE s.product_id = :productId ORDER BY s.supply_id DESC LIMIT 1", nativeQuery = true)
    Optional<SupplyEntity> findLastSupplyByProductId(@Param(DrivenConstants.PARAM_SUPPLY_PRODUCT_ID) Long productId);
}
