package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table

/**
 * Created by simon on 9/13/15.
 */

@Table(database = ListMakerDatabase::class)
class UserModel : ListMakerModel() {

    @Column()
    var username: String? = null

    @Column()
    var password: String? = null

    @Column()
    var email: String? = null

    @Column()
    var phone: String? = null

    @Column()
    var sessionToken: String? = null
}
