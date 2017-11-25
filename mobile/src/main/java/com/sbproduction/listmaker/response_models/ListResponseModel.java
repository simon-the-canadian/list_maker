package com.sbproduction.listmaker.response_models;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.sbproduction.listmakerpro.database.ListModel;

import java.util.ArrayList;

/**
 * Created by simon on 1/1/16.
 */

@JsonObject
public class ListResponseModel implements BaseResponseModel<ListModel>
{
	@JsonField(name = "results")
	private ArrayList<ListModel> results;

	@Nullable
	public ArrayList<ListModel> getResults()
	{
		return results;
	}

	public void setResults(ArrayList<ListModel> results)
	{
		this.results = results;
	}
}
