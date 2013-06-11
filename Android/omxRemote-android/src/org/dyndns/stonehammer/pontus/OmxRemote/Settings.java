package org.dyndns.stonehammer.pontus.OmxRemote;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Settings extends Activity {
	EditText host, port;
	public final static String PREFS_NAME="Settings_file";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	
		host = (EditText)findViewById(R.id.host);
		port = (EditText)findViewById(R.id.port);
		SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		host.setText(sharedPrefs.getString("Host", "0.0.0.0"));
		port.setText(String.valueOf(sharedPrefs.getInt("Port", 8080)));
	}
	public void save(View view) {
		SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putString("Host", ((EditText)findViewById(R.id.host)).getText().toString());
		editor.putInt("Port", Integer.parseInt(((EditText)findViewById(R.id.port)).getText().toString()));
		editor.commit();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
