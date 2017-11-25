package com.sbproduction.listmaker.database;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.InheritedColumn;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by simon on 9/7/15.
 */

@JsonObject
@Table(databaseName = ListMakerDatabase.sName, inheritedColumns = {
		@InheritedColumn(column = @Column, fieldName = "mId"),
		@InheritedColumn(column = @Column, fieldName = "mIsNew"),
		@InheritedColumn(column = @Column, fieldName = "mIsUpdated"),
		@InheritedColumn(column = @Column, fieldName = "mObjectId"),
		@InheritedColumn(column = @Column, fieldName = "mAuthentication"),
		@InheritedColumn(column = @Column, fieldName = "mCreatedAt"),
		@InheritedColumn(column = @Column, fieldName = "mUpdatedAt")
})
public class ListModel extends ListMakerModel
{
	public static final String sListId = "id";
	public static final String sListName = "list_name";

	private static String sUrl = "List";


	@JsonField(name = sListId)
	@Column(name = sListId, getterName = "getListId", setterName = "setListId")
	private String mListId;

	@JsonField(name = sListName)
	@Column(name = sListName, getterName = "getListName", setterName = "setListName")
	private String mListName;

	@Nullable
	public String getListId()
	{
		return mListId;
	}

	public void setListId(String listId)
	{
		mListId = listId;
	}

	@Nullable
	public String getListName()
	{
		return mListName;
	}

	public void setListName(String listName)
	{
		mListName = listName;
	}

	public static String getModelUrl()
	{
		return sUrl;
	}

	@Override
	public String getUrl()
	{
		return sUrl;
	}
}
