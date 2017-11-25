package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table

/**
 * Created by simon on 9/7/15.
 */
@Table(database = ListMakerDatabase::class)
class ListItemModel : ListMakerModel() {

    @Column
    var listId: String? = null

    @Column
    var itemName: String? = null

    @Column
    var groupId: String? = null
}
