package com.sbproduction.listmaker.database;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.InheritedColumn;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by simon on 9/13/15.
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
public class UserModel extends ListMakerModel
{
	public static final String sUsername = "username";
	public static final String sPassword = "password";
	public static final String sEmail = "email";
	public static final String sPhone = "phone";
	public static final String sSessionToken = "sessionToken";

	private static final String sUrl = "users";

	@JsonField(name = sUsername)
	@Column(name = sUsername, getterName = "getUsername", setterName = "setUsername")
	private String mUsername;

	@JsonField(name = sPassword)
	@Column(name = sPassword, getterName = "getPassword", setterName = "setPassword")
	private String mPassword;

	@JsonField(name = sEmail)
	@Column(name = sEmail, getterName = "getEmail", setterName = "setEmail")
	private String mEmail;

	@JsonField(name = sPhone)
	@Column(name = sPhone, getterName = "getPhone", setterName = "setPhone")
	private String mPhone;

	@JsonField(name = sSessionToken)
	@Column(name = sSessionToken, getterName = "getSessionToken", setterName = "setSessionToken")
	private String mSessionToken;

	@Nullable
	public String getUsername()
	{
		return mUsername;
	}

	public void setUsername(String username)
	{
		mUsername = username;
	}

	@Nullable
	public String getPhone()
	{
		return mPhone;
	}

	public void setPhone(String phone)
	{
		mPhone = phone;
	}

	@Nullable
	public String getSessionToken()
	{
		return mSessionToken;
	}

	public void setSessionToken(String sessionToken)
	{
		mSessionToken = sessionToken;
	}

	@Nullable
	public String getPassword()
	{
		return mPassword;
	}

	public void setPassword(String password)
	{
		mPassword = password;
	}

	@Nullable
	public String getEmail()
	{
		return mEmail;
	}

	public void setEmail(String email)
	{
		mEmail = email;
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
