package com.sbproduction.listmaker.database;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.sbproduction.listmakerpro.tools.ACLConverter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by simon on 9/7/15.
 */
@JsonObject
public abstract class ListMakerModel extends BaseModel
{
	public static final String sId = "_id";
	public static final String kObjectId = "objectId";
	public static final String sIsNew = "is_new";
	public static final String sIsUpdated = "is_updated";
	public static final String sAuthentication = "ACL";
	public static final String sCreatedAt = "createdAt";
	public static final String sUpdatedAt = "updatedAt";


	@Column(name = sId)
	@PrimaryKey(autoincrement = true)
	long mId;

	@Column(name = sIsNew, getterName = "isNew", setterName = "setIsNew")
	boolean mIsNew;

	@Column(name = sIsUpdated, getterName = "isUpdated", setterName = "setIsUpdated")
	boolean mIsUpdated;

	@JsonField(name = kObjectId)
	@Column(name = kObjectId)
	String mObjectId;

	@JsonField(name = sAuthentication, typeConverter = ACLConverter.class)
	@Column(name = sAuthentication, getterName = "getAuthentication", setterName = "setAuthentication")
	String mAuthentication;

	@JsonField(name = sCreatedAt)
	@Column(name = sCreatedAt, getterName = "getCreatedAt", setterName = "setCreatedAt")
	String mCreatedAt;

	@JsonField(name = sUpdatedAt)
	@Column(name = sUpdatedAt, getterName = "getUpdatedAt", setterName = "setUpdatedAt")
	String mUpdatedAt;

	public boolean isNew()
	{
		return mIsNew;
	}

	public void setIsNew(boolean isNew)
	{
		mIsNew = isNew;
	}

	public boolean isUpdated()
	{
		return mIsUpdated;
	}

	public void setIsUpdated(boolean isUpdated)
	{
		//don't set to updated if the object is still new
		if (!isNew() || !isUpdated)
		{
			mIsUpdated = isUpdated;
		}
	}

	@Nullable
	public String getObjectId()
	{
		return mObjectId;
	}

	public void setObjectId(String objectId)
	{
		mObjectId = objectId;
	}

	public void setAuthenticatedUser(String userId)
	{
		JSONObject acl;

		try
		{
			if (TextUtils.isEmpty(mAuthentication))
			{
				acl = new JSONObject();
			}
			else
			{
				acl = new JSONObject(mAuthentication);
			}

			JSONObject authMap;

			if (acl.has(userId))
			{
				authMap = acl.getJSONObject(userId);
			}
			else
			{
				authMap = new JSONObject();
			}

			authMap.put("read", true);
			authMap.put("write", true);

			acl.put(userId, authMap);

			mAuthentication = acl.toString();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	@Nullable
	public String getAuthentication()
	{
		return mAuthentication;
	}

	public void setAuthentication(String authentication)
	{
		mAuthentication = authentication;
	}

	@Nullable
	public String getCreatedAt()
	{
		return mCreatedAt;
	}

	public void setCreatedAt(String createdAt)
	{
		mCreatedAt = createdAt;
	}

	@Nullable
	public String getUpdatedAt()
	{
		return mUpdatedAt;
	}

	public void setUpdatedAt(String updatedAt)
	{
		mUpdatedAt = updatedAt;
	}

	public abstract String getUrl();
}
