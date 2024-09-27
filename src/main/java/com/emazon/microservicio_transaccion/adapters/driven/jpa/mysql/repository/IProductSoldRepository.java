package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.ProductSoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductSoldRepository extends JpaRepository<ProductSoldEntity, Long> {
}
