package com.sbproduction.listmaker.database

import com.raizlabs.android.dbflow.sql.language.Select

/**
 * Created by simonbergeron on 11/25/17.
 */

/**
 * Gets all active list in the current active workspace.
 *
 * @param workspaceId the id of the workspace that the user is in
 * @return all active lists in the workspace
 */
fun getActiveLists(workspaceId: String): List<ListModel> {
    return Select()
            .from(ListModel::class.java)
            .where(ListModel_Table.workspaceId.eq(workspaceId))
            .and(ListModel_Table.deletedAt.isNull)
            .queryList()
}
