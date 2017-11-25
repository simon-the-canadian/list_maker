package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by simon on 9/7/15.
 */

@Database(name = ListMakerDatabase.name, version = ListMakerDatabase.version)
object ListMakerDatabase {
    const val name = "list_maker_db"
    const val version = 1
}
