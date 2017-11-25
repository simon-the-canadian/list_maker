package com.sbproduction.listmaker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sbproduction.listmakerpro.R;
import com.sbproduction.listmakerpro.database.ListModel;
import com.sbproduction.listmakerpro.network.HttpManager;

/**
 * Created by simon on 9/13/15.
 */
public class ListActivity extends AppCompatActivity
{
	private Button mAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_list_layout);

		dataToSync();
		setupUI();
		setupClickListeners();
	}

	private void dataToSync()
	{
		HttpManager.get(ListModel.class, ListModel.getModelUrl(), null);
	}

	private void setupUI()
	{
		mAdd = (Button) findViewById(R.id.add_list);
	}

	private void setupClickListeners()
	{
		mAdd.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(ListActivity.this, AddListActivity.class);
				startActivity(intent);
			}
		});
	}
}
