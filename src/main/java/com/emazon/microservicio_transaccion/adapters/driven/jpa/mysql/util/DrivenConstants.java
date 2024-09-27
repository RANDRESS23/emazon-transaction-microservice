package com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.util;

public class DrivenConstants {
    private DrivenConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

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

    public static final String SALE_TABLE_NAME = "sale";
    public static final String COLUMN_SALE_ID = "sale_id";
    public static final String COLUMN_SALE_CLIENT_ID = "client_id";
    public static final String COLUMN_SALE_CLIENT_EMAIL = "email";
    public static final String COLUMN_SALE_TOTAL_QUANTITY = "total_quantity";
    public static final String COLUMN_SALE_TOTAL_PRICE = "total_price";
    public static final String COLUMN_SALE_DATE = "sale_date";

    public static final String PRODUCT_SOLD_TABLE_NAME = "products_sold";
    public static final String COLUMN_PRODUCT_SOLD_ID = "product_sold_id";
    public static final String COLUMN_PRODUCT_SOLD_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_SOLD_NAME = "name";
    public static final String COLUMN_PRODUCT_SOLD_QUANTITY = "quantity";
    public static final String COLUMN_PRODUCT_SOLD_UNIT_PRICE = "unit_price";
    public static final String COLUMN_PRODUCT_SOLD_TOTAL_PRICE = "total_price";

    public static final String SUPPLY_NOT_FOUND = "Supply not found";

    public static final String AUTHORIZATION_HEADER  = "Authorization";

    public static final String PARAM_SUPPLY_PRODUCT_ID = "productId";

    public static final String INVALID_EMAIL = "The 'email' field is not valid";
}
