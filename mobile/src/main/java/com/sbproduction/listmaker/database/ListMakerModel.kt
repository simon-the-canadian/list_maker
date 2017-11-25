package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.structure.BaseModel

/**
 * Created by simon on 9/7/15.
 */
abstract class ListMakerModel : BaseModel() {
    @PrimaryKey
    lateinit var id: String

    @Column
    var isNew: Boolean = false

    @Column
    var isUpdated: Boolean = false

    @Column
    var authentication: String? = null

    @Column
    var createdAt: String? = null

    @Column
    var updatedAt: String? = null

    @Column
    var deletedAt: String? = null
}
