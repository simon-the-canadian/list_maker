package com.sbproduction.listmaker.response_models;

import android.support.annotation.Nullable;

import com.sbproduction.listmakerpro.database.ListMakerModel;

import java.util.ArrayList;

/**
 * Created by simon on 1/1/16.
 */
public interface BaseResponseModel<T extends ListMakerModel>
{
	@Nullable
	ArrayList<T> getResults();

	void setResults(ArrayList<T> results);
}
