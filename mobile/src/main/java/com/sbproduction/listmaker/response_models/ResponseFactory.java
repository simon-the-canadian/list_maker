package com.sbproduction.listmaker.response_models;

import com.sbproduction.listmakerpro.database.GroupModel;
import com.sbproduction.listmakerpro.database.ListItemModel;
import com.sbproduction.listmakerpro.database.ListMakerModel;
import com.sbproduction.listmakerpro.database.ListModel;

/**
 * Created by simon on 1/1/16.
 */
public class ResponseFactory
{
	public static Class<? extends BaseResponseModel> getResponseClass(Class<? extends ListMakerModel> className)
	{
		if (className.equals(ListModel.class))
		{
			return ListResponseModel.class;
		}
		else if (className.equals(ListItemModel.class))
		{
			return ListItemResponseModel.class;
		}
		else if (className.equals(GroupModel.class))
		{
			return GroupResponseModel.class;
		}

		throw new UnsupportedOperationException("This model is not currently supported");
	}
}
