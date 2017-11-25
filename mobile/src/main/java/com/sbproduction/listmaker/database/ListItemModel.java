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
public class ListItemModel extends ListMakerModel
{
	public static final String sListItemId = "id";
	public static final String sListItemName = "list_name";
	public static final String sGroupId = "group_id";

	private static final String sUrl = "ListItem";

	@JsonField(name = sListItemId)
	@Column(name = sListItemId, getterName = "getItemId", setterName = "setItemId")
	private String mItemId;

	@JsonField(name = sListItemName)
	@Column(name = sListItemName, getterName = "getItemName", setterName = "setItemName")
	private String mItemName;

	@JsonField(name = sGroupId)
	@Column(name = sGroupId, getterName = "getGroupId", setterName = "setGroupId")
	private String mGroupId;

	@Nullable
	public String getItemId()
	{
		return mItemId;
	}

	public void setItemId(String itemId)
	{
		mItemId = itemId;
	}

	@Nullable
	public String getItemName()
	{
		return mItemName;
	}

	public void setItemName(String itemName)
	{
		mItemName = itemName;
	}

	@Nullable
	public String getGroupId()
	{
		return mGroupId;
	}

	public void setGroupId(String groupId)
	{
		mGroupId = groupId;
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
