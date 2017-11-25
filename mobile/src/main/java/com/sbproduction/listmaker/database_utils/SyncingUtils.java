package com.sbproduction.listmaker.database_utils;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.sbproduction.listmakerpro.database.ListMakerModel;
import com.sbproduction.listmakerpro.database.ListModel;

import java.util.List;

/**
 * Created by simon on 9/26/15.
 */
public class SyncingUtils
{
	public static <ListClass extends ListMakerModel> List<ListClass> getListsToSync(Class<ListClass> listMakerClass)
	{
		return new Select()
				.from(listMakerClass)
				.where(ListMakerModel.sIsNew + " = ? OR " + ListMakerModel.sIsUpdated + " = ?", true, true)
				.queryList();
	}

	private void test()
	{
		List<ListModel> lists = getListsToSync(ListModel.class);
	}
}
