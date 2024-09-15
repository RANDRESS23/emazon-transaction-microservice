package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {
}
