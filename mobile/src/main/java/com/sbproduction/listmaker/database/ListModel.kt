package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table

/**
 * Created by simon on 9/7/15.
 */

@Table(database = ListMakerDatabase::class)
class ListModel : ListMakerModel() {
    @Column
    var name: String? = null

    @Column
    var workspaceId: String? = null
}
