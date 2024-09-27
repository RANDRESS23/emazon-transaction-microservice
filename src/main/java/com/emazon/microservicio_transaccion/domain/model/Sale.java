package com.emazon.microservicio_transaccion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Sale {
    private final Long saleId;
    private Long clientId;
    private String email;
    private Long totalQuantity;
    private BigDecimal totalPrice;
    private LocalDateTime date;
    private List<ProductSold> products;

    public Sale(Long saleId, Long clientId, String email, Long totalQuantity, BigDecimal totalPrice, LocalDateTime date, List<ProductSold> products) {
        this.saleId = saleId;
        this.clientId = clientId;
        this.email = email;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.products = products;
    }

    public Long getSaleId() {
        return saleId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<ProductSold> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSold> products) {
        this.products = products;
    }
}
