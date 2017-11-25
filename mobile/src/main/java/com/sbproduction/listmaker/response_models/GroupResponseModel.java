package com.sbproduction.listmaker.response_models;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.sbproduction.listmakerpro.database.GroupModel;

import java.util.ArrayList;

/**
 * Created by simon on 1/1/16.
 */

@JsonObject
public class GroupResponseModel implements BaseResponseModel<GroupModel>
{
	@JsonField(name = "results")
	private ArrayList<GroupModel> results;

	@Nullable
	public ArrayList<GroupModel> getResults()
	{
		return results;
	}

	public void setResults(ArrayList<GroupModel> results)
	{
		this.results = results;
	}
}
