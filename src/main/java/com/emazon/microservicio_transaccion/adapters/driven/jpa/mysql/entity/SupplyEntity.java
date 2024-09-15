package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = DrivenConstants.SUPPLY_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_SUPPLY_ID)
    private Long supplyId;

    @Column(name = DrivenConstants.COLUMN_SUPPLY_PRODUCT_ID, nullable = false)
    private Long productId;

    @Column(name = DrivenConstants.COLUMN_SUPPLY_EXTRA_QUANTITY, nullable = false)
    private Long extraQuantity;

    @Column(name = DrivenConstants.COLUMN_SUPPLY_AUX_BODEGA_ID, nullable = false)
    private Long auxBodegaId;

    @Column(name = DrivenConstants.COLUMN_SUPPLY_DATE, nullable = false)
    private LocalDate date;

    @Column(name = DrivenConstants.COLUMN_SUPPLY_HOUR, nullable = false)
    private LocalTime hour;

    @ManyToOne
    @JoinColumn(name = DrivenConstants.COLUMN_SUPPLY_STATE_ID)
    private StateEntity state;

    @Column(name = DrivenConstants.COLUMN_SUPPLY_FAILURE_REASON)
    private String failureReason;
}
