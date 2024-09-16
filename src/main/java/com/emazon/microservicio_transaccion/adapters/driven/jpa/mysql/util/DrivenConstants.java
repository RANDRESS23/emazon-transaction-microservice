package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util;

public class DrivenConstants {
    private DrivenConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String STATE_TABLE_NAME = "state";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_STATE_NAME = "name";

    public static final String SUPPLY_TABLE_NAME = "supply";
    public static final String COLUMN_SUPPLY_ID = "supply_id";
    public static final String COLUMN_SUPPLY_PRODUCT_ID = "product_id";
    public static final String COLUMN_SUPPLY_EXTRA_QUANTITY = "quantity";
    public static final String COLUMN_SUPPLY_AUX_BODEGA_ID = "aux_bodega_id";
    public static final String COLUMN_SUPPLY_DATE = "date";
    public static final String COLUMN_SUPPLY_HOUR = "hour";
    public static final String COLUMN_SUPPLY_STATE_ID = "state_id";
    public static final String COLUMN_SUPPLY_FAILURE_REASON = "failure_reason";

    public static final String SUPPLY_NOT_FOUND = "Supply not found";

    public static final String AUTHORIZATION_HEADER  = "Authorization";
}
