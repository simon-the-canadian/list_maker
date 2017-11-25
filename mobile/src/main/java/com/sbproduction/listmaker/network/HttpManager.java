package com.sbproduction.listmaker.network;

import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.runtime.transaction.process.ProcessModelInfo;
import com.raizlabs.android.dbflow.runtime.transaction.process.SaveModelTransaction;
import com.sbproduction.listmakerpro.database.ListMakerModel;
import com.sbproduction.listmakerpro.database.UserModel;
import com.sbproduction.listmakerpro.response_models.BaseResponseModel;
import com.sbproduction.listmakerpro.response_models.ResponseFactory;
import com.sbproduction.listmakerpro.tools.Preferences;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by simon on 9/12/15.
 */
public class HttpManager
{
	private static final String kBaseUrl = "https://www.parse.com/1/classes/";

	private static OkHttpClient mClient;

	private static OkHttpClient getClient()
	{
		if (mClient == null)
		{
			mClient = new OkHttpClient();

			mClient.setConnectTimeout(20, TimeUnit.SECONDS);
			mClient.setWriteTimeout(20, TimeUnit.SECONDS);
			mClient.setReadTimeout(30, TimeUnit.SECONDS);
			mClient.networkInterceptors().add(new StethoInterceptor());
		}

		return mClient;
	}

	private static Request.Builder getDefaultRequestBuilder(String url)
	{
		return new Request.Builder()
				.url(kBaseUrl + url)
				.header("X-Parse-Application-Id", "pcnmjiTgqRQXOxhHEVoR3oy07gf3LfY6st7bal2a")
				.addHeader("X-Parse-REST-API-Key", "OC0Hc6WidFl2Ifen8iG9UyMH2NgYIol9RTyfxck7")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Content-Type", "application/json");
	}

	private static Request.Builder getDefaultRequestBuilder(HttpUrl url)
	{
		return new Request.Builder()
				.url(url)
				.header("X-Parse-Application-Id", "pcnmjiTgqRQXOxhHEVoR3oy07gf3LfY6st7bal2a")
				.addHeader("X-Parse-REST-API-Key", "OC0Hc6WidFl2Ifen8iG9UyMH2NgYIol9RTyfxck7")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Content-Type", "application/json");
	}

	public static void login(String username, String password, final Callback delegate)
	{
		HttpUrl url = new HttpUrl.Builder()
				.scheme("https")
				.host("www.parse.com")
				.addPathSegment("1/login")
				.addEncodedQueryParameter("username", username)
				.addEncodedQueryParameter("password", password)
				.build();

		Request request = getDefaultRequestBuilder(url)
				.get()
				.build();

		getClient().newCall(request).enqueue(new Callback()
		{
			@Override
			public void onFailure(Request request, IOException e)
			{
				delegate.onFailure(request, e);
			}

			@Override
			public void onResponse(Response response) throws IOException
			{
				if (response != null && response.isSuccessful())
				{
					UserModel loginUser = LoganSquare.parse(response.body().byteStream(), UserModel.class);
					loginUser.save();

					Preferences.setUsername(loginUser.getUsername());
					Preferences.setSessionToken(loginUser.getSessionToken());
					Preferences.setAuthId(loginUser.getObjectId());
				}

				delegate.onResponse(response);
			}
		});
	}

	public static void get(final Class<? extends ListMakerModel> modelClass, String modelUrl, final Callback delegate)
	{
		Request request = getDefaultRequestBuilder(modelUrl)
				.addHeader("X-Parse-Session-Token", Preferences.getSessionToken())
				.get()
				.build();

		getClient().newCall(request).enqueue(new Callback()
		{
			@Override
			public void onFailure(Request request, IOException e)
			{
				if (delegate != null)
				{
					delegate.onFailure(request, e);
				}
			}

			@Override
			public void onResponse(Response response) throws IOException
			{
				if (delegate != null)
				{
					delegate.onResponse(response);
				}

				if (!response.isSuccessful())
				{
					Log.e("LoginActivity", "Request failed: error " + response.code() + " - " + response.message());
				}
				else
				{
//					String test = response.body().string();
					//get the models from the server response
					BaseResponseModel retrievedModels = LoganSquare.parse(response.body().byteStream(), ResponseFactory.getResponseClass(modelClass));

					//save the models in the database
					TransactionManager.getInstance().addTransaction(new SaveModelTransaction<>(ProcessModelInfo.withModels(retrievedModels.getResults())));
				}
			}
		});
	}

	public static void post(final ListMakerModel model, final Callback delegate)
	{
		model.setAuthenticatedUser(Preferences.getAuthId());
		model.setIsNew(true);
		model.save();

		try
		{
			String postData = LoganSquare.serialize(model);

			Request request = getDefaultRequestBuilder(model.getUrl())
					.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postData))
					.build();

			getClient().newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Request request, IOException e)
				{
					if (delegate != null)
					{
						delegate.onFailure(request, e);
					}
				}

				@Override
				public void onResponse(Response response) throws IOException
				{
					if (response != null && response.isSuccessful())
					{
						model.setIsNew(false);
					}

					if (delegate != null)
					{
						delegate.onResponse(response);
					}
				}
			});
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void createUser(UserModel model, Callback delegate)
	{
		String postData = null;
		try
		{
			postData = LoganSquare.serialize(model);

			Request request = new Request.Builder()
					.url("https://www.parse.com/1/" + model.getUrl())
					.header("X-Parse-Application-Id", "pcnmjiTgqRQXOxhHEVoR3oy07gf3LfY6st7bal2a")
					.addHeader("X-Parse-REST-API-Key", "OC0Hc6WidFl2Ifen8iG9UyMH2NgYIol9RTyfxck7")
					.addHeader("Accept", "application/json; q=0.5")
					.addHeader("Content-Type", "application/json")
					.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postData))
					.build();

			getClient().newCall(request).enqueue(delegate);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
