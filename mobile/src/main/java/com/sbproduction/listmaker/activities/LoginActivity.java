package com.sbproduction.listmaker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sbproduction.listmaker.R;

public class LoginActivity extends AppCompatActivity
{
	private EditText mUsername;
	private EditText mPassword;
	private Button mLogin;
	private Button mSignUp;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		setupUI();
	}

	private void setupUI()
	{
		mUsername = (EditText) findViewById(R.id.login_username);
		mPassword = (EditText) findViewById(R.id.login_password);
		mLogin = (Button) findViewById(R.id.login_btn);
		mSignUp = (Button) findViewById(R.id.login_signup_btn);

		mLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				login();
			}
		});

		mSignUp.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(intent);
			}
		});
	}

	private void login()
	{
		String username = mUsername.getText().toString();
		String password = mPassword.getText().toString();

//		HttpManager.login(username, password, new Callback()
//		{
//			@Override
//			public void onFailure(Request request, IOException e)
//			{
//				Log.e("LoginActivity", "Request failed: " + e.toString());
//			}
//
//			@Override
//			public void onResponse(Response response) throws IOException
//			{
//				if (!response.isSuccessful())
//				{
//					Log.e("LoginActivity", "Request failed: error " + response.code() + " - " + response.message());
//				}
//				else
//				{
//					Intent intent = new Intent(LoginActivity.this, ListActivity.class);
//					startActivity(intent);
//				}
//			}
//		});
	}
}
