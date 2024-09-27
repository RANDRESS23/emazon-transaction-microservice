package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
}
