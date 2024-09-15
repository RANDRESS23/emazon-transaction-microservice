package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.StateEntity;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStateRepository extends JpaRepository<StateEntity, Long> {
    Optional<StateEntity> findByName(StateEnum name);
}
