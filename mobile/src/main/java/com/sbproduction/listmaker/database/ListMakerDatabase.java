package com.sbproduction.listmaker.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by simon on 9/7/15.
 */

@Database(name = ListMakerDatabase.sName, version = ListMakerDatabase.sVersion)
public class ListMakerDatabase
{
	public static final String sName = "list_maker_pro_db";

	public static final int sVersion = 1;
}
