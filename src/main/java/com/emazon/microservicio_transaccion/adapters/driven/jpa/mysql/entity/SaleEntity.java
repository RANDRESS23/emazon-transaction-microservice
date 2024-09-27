package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = DrivenConstants.SALE_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_SALE_ID)
    private Long saleId;

    @Column(name = DrivenConstants.COLUMN_SALE_CLIENT_ID, nullable = false)
    private Long clientId;

    @Column(name = DrivenConstants.COLUMN_SALE_CLIENT_EMAIL, nullable = false)
    @Pattern(regexp = DrivenConstants.EMAIL_REGEX, message = DrivenConstants.INVALID_EMAIL)
    private String email;

    @Column(name = DrivenConstants.COLUMN_SALE_TOTAL_QUANTITY, nullable = false)
    private Long totalQuantity;

    @Column(name = DrivenConstants.COLUMN_SALE_TOTAL_PRICE, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = DrivenConstants.COLUMN_SALE_DATE, nullable = false)
    private LocalDateTime date;
}
