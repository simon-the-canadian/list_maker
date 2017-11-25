package com.sbproduction.listmaker.response_models;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.sbproduction.listmakerpro.database.ListItemModel;

import java.util.ArrayList;

/**
 * Created by simon on 1/1/16.
 */

@JsonObject
public class ListItemResponseModel implements BaseResponseModel<ListItemModel>
{
	@JsonField(name = "results")
	private ArrayList<ListItemModel> results;

	@Nullable
	public ArrayList<ListItemModel> getResults()
	{
		return results;
	}

	public void setResults(ArrayList<ListItemModel> results)
	{
		this.results = results;
	}
}
