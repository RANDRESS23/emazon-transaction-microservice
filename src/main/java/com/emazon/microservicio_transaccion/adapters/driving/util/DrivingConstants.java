package com.emazon.microservicio_transaccion.adapters.driving.util;

public class DrivingConstants {
    private DrivingConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String HAS_ROLE_AUX_BODEGA_AND_ADMIN = "hasAnyRole('AUX_BODEGA', 'ADMIN')";
    public static final String HAS_ROLE_CLIENT = "hasRole('CLIENTE')";
    public static final String SUPPLY_ID = "supplyId";

    public static final String RESPONSE_CODE_201="201";
    public static final String RESPONSE_CODE_400="400";
    public static final String RESPONSE_CODE_404="404";

    public static final String TAG_SUPPLY_NAME = "Supply";
    public static final String TAG_SUPPLY_DESCRIPTION = "Supply API";
    public static final String SAVE_SUPPLY_SUMMARY = "Save a new supply";
    public static final String SAVE_SUPPLY_DESCRIPTION = "Creates a new supply in the database";
    public static final String SAVE_SUPPLY_RESPONSE_201_DESCRIPTION = "Supply created successfully";
    public static final String SAVE_SUPPLY_RESPONSE_400_DESCRIPTION = "Invalid input";

    public static final String GET_SUPPLY_SUMMARY = "Get supply";
    public static final String GET_SUPPLY_DESCRIPTION = "Recover a supply";
    public static final String GET_SUPPLY_RESPONSE_201_DESCRIPTION = "Supply retrieved successfully";
    public static final String GET_SUPPLY_RESPONSE_400_DESCRIPTION = "Invalid param";
    public static final String GET_SUPPLY_RESPONSE_404_DESCRIPTION = "Supply not found";

    public static final String TAG_SALE_NAME = "Sale";
    public static final String TAG_SALE_DESCRIPTION = "Sale API";
    public static final String SAVE_SALE_SUMMARY = "Save a new sale";
    public static final String SAVE_SALE_DESCRIPTION = "Creates a new sale in the database";
    public static final String SAVE_SALE_RESPONSE_201_DESCRIPTION = "Sale created successfully";
    public static final String SAVE_SALE_RESPONSE_400_DESCRIPTION = "Invalid input";
}
