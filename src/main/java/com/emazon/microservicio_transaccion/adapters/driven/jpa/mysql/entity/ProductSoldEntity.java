package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = DrivenConstants.PRODUCT_SOLD_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSoldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_PRODUCT_SOLD_ID)
    private Long productSolId;

    @Column(name = DrivenConstants.COLUMN_SALE_ID, nullable = false)
    private Long saleId;

    @Column(name = DrivenConstants.COLUMN_PRODUCT_SOLD_PRODUCT_ID, nullable = false)
    private Long productId;

    @Column(name = DrivenConstants.COLUMN_PRODUCT_SOLD_NAME, nullable = false)
    private String name;

    @Column(name = DrivenConstants.COLUMN_PRODUCT_SOLD_QUANTITY, nullable = false)
    private Long quantity;

    @Column(name = DrivenConstants.COLUMN_PRODUCT_SOLD_UNIT_PRICE, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = DrivenConstants.COLUMN_PRODUCT_SOLD_TOTAL_PRICE, nullable = false)
    private BigDecimal totalPrice;
}
