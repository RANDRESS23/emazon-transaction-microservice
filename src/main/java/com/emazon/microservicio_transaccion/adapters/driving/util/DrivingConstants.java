package com.emazon.microservicio_transaccion.adapters.driving.util;

public class DrivingConstants {
    private DrivingConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String HAS_ROLE_AUX_BODEGA = "hasRole('AUX_BODEGA')";
    public static final String SUPPLY_ID = "supplyId";

    public static final String RESPONSE_CODE_201="201";
    public static final String RESPONSE_CODE_400="400";

    public static final String TAG_SUPPLY_NAME = "Supply";
    public static final String TAG_SUPPLY_DESCRIPTION = "Supply API";
    public static final String SAVE_SUPPLY_SUMMARY = "Save a new supply";
    public static final String SAVE_SUPPLY_DESCRIPTION = "Creates a new supply in the database";
    public static final String SAVE_SUPPLY_RESPONSE_201_DESCRIPTION = "Supply created successfully";
    public static final String SAVE_SUPPLY_RESPONSE_400_DESCRIPTION = "Invalid input";
}
