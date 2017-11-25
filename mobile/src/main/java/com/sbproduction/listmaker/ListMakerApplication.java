package com.sbproduction.listmaker;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.sbproduction.listmaker.tools.Preferences;

/**
 * Created by simon on 9/7/15.
 */
public class ListMakerApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();

		Preferences.init(getApplicationContext());

		FlowConfig.Builder flowConfigBuilder = new FlowConfig.Builder(this).openDatabasesOnInit(true);
		FlowManager.init(flowConfigBuilder.build());

		Stetho.initialize(
				Stetho.newInitializerBuilder(this)
						.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
						.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
						.build());
	}
}
