package com.sbproduction.listmaker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bluelinelabs.logansquare.LoganSquare;
import com.sbproduction.listmakerpro.R;
import com.sbproduction.listmakerpro.database.UserModel;
import com.sbproduction.listmakerpro.network.HttpManager;
import com.sbproduction.listmakerpro.tools.Preferences;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by simon on 9/13/15.
 */
public class SignUpActivity extends AppCompatActivity
{
	private EditText mUsername;
	private EditText mPassword;
	private EditText mEmail;
	private Button mSignup;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		setupUI();
	}

	private void setupUI()
	{
		mUsername = (EditText) findViewById(R.id.signup_username);
		mPassword = (EditText) findViewById(R.id.signup_password);
		mEmail = (EditText) findViewById(R.id.signup_email);
		mSignup = (Button) findViewById(R.id.signup_btn);

		mSignup.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				createAccount();
			}
		});
	}

	private void createAccount()
	{
		UserModel user = new UserModel();
		user.setUsername(mUsername.getText().toString());
		user.setPassword(mPassword.getText().toString());
		user.setEmail(mEmail.getText().toString());

		HttpManager.createUser(user, new Callback()
		{
			@Override
			public void onFailure(Request request, IOException e)
			{
				Log.e("SignupActivity", "Request failed: " + e.toString());
			}

			@Override
			public void onResponse(Response response) throws IOException
			{
				if (!response.isSuccessful())
				{
					Log.e("LoginActivity", "Request failed: error " + response.code() + " - " + response.message());
				}
				else
				{
					UserModel loginResponse = LoganSquare.parse(response.body().byteStream(), UserModel.class);

					Preferences.setUsername(mUsername.getText().toString());
					Preferences.setSessionToken(loginResponse.getSessionToken());

					Intent intent = new Intent(SignUpActivity.this, ListActivity.class);
					startActivity(intent);
				}
			}
		});
	}
}
