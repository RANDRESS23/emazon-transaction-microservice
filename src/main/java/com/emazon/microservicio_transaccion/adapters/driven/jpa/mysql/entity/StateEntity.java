package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = DrivenConstants.STATE_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StateEntity implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_STATE_ID)
    private Long stateId;

    @Column(name = DrivenConstants.COLUMN_STATE_NAME, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StateEnum name;
}
