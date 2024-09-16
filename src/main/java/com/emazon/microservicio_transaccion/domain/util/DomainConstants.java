package com.emazon.microservicio_transaccion.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_PRODUCT_ID_NULL_MESSAGE = "Field 'productId' cannot be null";
    public static final String FIELD_EXTRA_QUANTITY_NULL_MESSAGE = "Field 'extraQuantity' cannot be null";
    public static final String FIELD_DATE_NULL_MESSAGE = "Field 'date' cannot be null";
    public static final String FIELD_HOUR_NULL_MESSAGE = "Field 'hour' cannot be null";

    public static final String STATE_NOT_FOUND = "State not found.";

    public static final String START_MESSAGE_SYMBOLS = "[{";
    public static final String PATH_MESSAGE = "message";
    public static final String PATH_STATUS = "status";
}
