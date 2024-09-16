package com.emazon.microservicio_transaccion.domain.model;

import com.emazon.microservicio_transaccion.domain.util.DomainConstants;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Objects.requireNonNull;

public class Supply {
    private final Long supplyId;
    private final Long productId;
    private final Long extraQuantity;
    private Long auxBodegaId;
    private final LocalDate date;
    private final LocalTime hour;
    private State state;
    private String failureReason;

    public Supply(SupplyBuilder builder) {
        this.supplyId = builder.supplyId;
        this.productId = requireNonNull(builder.productId, DomainConstants.FIELD_PRODUCT_ID_NULL_MESSAGE);
        this.extraQuantity = requireNonNull(builder.extraQuantity, DomainConstants.FIELD_EXTRA_QUANTITY_NULL_MESSAGE);
        this.auxBodegaId = builder.auxBodegaId;
        this.date = requireNonNull(builder.date, DomainConstants.FIELD_DATE_NULL_MESSAGE);
        this.hour = requireNonNull(builder.hour, DomainConstants.FIELD_HOUR_NULL_MESSAGE);
        this.failureReason = builder.failureReason;
        this.state = builder.state;
    }

    public static class SupplyBuilder {
        private Long supplyId;
        private Long productId;
        private Long extraQuantity;
        private Long auxBodegaId;
        private LocalDate date;
        private LocalTime hour;
        private State state;
        private String failureReason;

        public SupplyBuilder supplyId(Long supplyId) {
            this.supplyId = supplyId;
            return this;
        }

        public SupplyBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public SupplyBuilder extraQuantity(Long extraQuantity) {
            this.extraQuantity = extraQuantity;
            return this;
        }

        public SupplyBuilder auxBodegaId(Long auxBodegaId) {
            this.auxBodegaId = auxBodegaId;
            return this;
        }

        public SupplyBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public SupplyBuilder hour(LocalTime hour) {
            this.hour = hour;
            return this;
        }

        public SupplyBuilder state(State state) {
            this.state = state;
            return this;
        }

        public SupplyBuilder failureReason(String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        public Supply build() {
            return new Supply(this);
        }
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getExtraQuantity() {
        return extraQuantity;
    }

    public Long getAuxBodegaId() {
        return auxBodegaId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public State getState() {
        return state;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setAuxBodegaId(Long auxBodegaId) {
        this.auxBodegaId = auxBodegaId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
