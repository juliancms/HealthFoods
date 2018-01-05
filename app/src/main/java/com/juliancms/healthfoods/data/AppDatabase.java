package com.juliancms.healthfoods.data;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by marines on 12/29/17.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "HealthFoods";

    public static final int VERSION = 1;
}