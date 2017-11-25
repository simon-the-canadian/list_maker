package com.sbproduction.listmaker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbproduction.listmaker.R;
import com.sbproduction.listmaker.database.ListModel;

/**
 * Created by simon on 1/10/16.
 */
public class AddListActivity extends AppCompatActivity
{
	private EditText mListName;
	private TextView mSaveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_list);

		setupUI();
		setupClickListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.save_menu, menu);

		LinearLayout saveMenu = (LinearLayout) menu.findItem(R.id.action_save).getActionView();
		mSaveButton = (TextView) saveMenu.findViewById(R.id.save_layout_text);
		mSaveButton.setEnabled(false);

		saveMenu.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mSaveButton.isEnabled())
				{
					handleActionSave();
				}
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

	private void setupUI()
	{
		mListName = (EditText) findViewById(R.id.add_list_list_name);
	}

	private void setupClickListeners()
	{
		mListName.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{}

			@Override
			public void afterTextChanged(Editable s)
			{
				mSaveButton.setEnabled(s.length() > 0);
			}
		});
	}

	private void handleActionSave()
	{
		ListModel newList = new ListModel();
		newList.setName(mListName.getText().toString());

//		HttpManager.post(newList, new Callback()
//		{
//			@Override
//			public void onFailure(Request request, IOException e)
//			{
//				if (request != null)
//				{
//
//				}
//			}
//
//			@Override
//			public void onResponse(Response response) throws IOException
//			{
//				if (response != null && response.isSuccessful())
//				{
//					String test = response.body().string();
//
//					if (test != null)
//					{
//
//					}
//				}
//			}
//		});
	}
}
