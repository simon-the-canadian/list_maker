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
public class GroupModel extends ListMakerModel
{
	public static final String sGroupId = "id";
	public static final String sGroupName = "group_name";

	private static final String sUrl = "Group";

	@JsonField(name = sGroupId)
	@Column(name = sGroupId, getterName = "getGroupId", setterName = "setGroupId")
	private String mGroupId;

	@JsonField(name = sGroupName)
	@Column(name = sGroupName, getterName = "getGroupName", setterName = "setGroupName")
	private String mGroupName;

	@Nullable
	public String getGroupId()
	{
		return mGroupId;
	}

	public void setGroupId(String groupId)
	{
		mGroupId = groupId;
	}

	@Nullable
	public String getGroupName()
	{
		return mGroupName;
	}

	public void setGroupName(String groupName)
	{
		mGroupName = groupName;
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
