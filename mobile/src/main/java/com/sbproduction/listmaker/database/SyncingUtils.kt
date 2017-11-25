package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.sql.language.Select

/**
 * Created by simon on 9/26/15.
 */

fun <ListClass : ListMakerModel> getItemsToSync(listMakerClass: Class<ListClass>): List<ListClass> {
    return Select()
            .from(listMakerClass)
            .where(ListModel_Table.isNew.`is`(true))
            .queryList()
}
