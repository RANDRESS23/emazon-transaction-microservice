package com.emazon.microservicio_transaccion.domain.model;

import java.math.BigDecimal;

public class ProductSold {
    private Long productSoldId;
    private Long saleId;
    private Long productId;
    private String name;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public ProductSold(Long productSoldId, Long saleId, Long productId, String name, Long quantity, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.productSoldId = productSoldId;
        this.saleId = saleId;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public Long getProductSoldId() {
        return productSoldId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setProductSoldId(Long productSoldId) {
        this.productSoldId = productSoldId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
