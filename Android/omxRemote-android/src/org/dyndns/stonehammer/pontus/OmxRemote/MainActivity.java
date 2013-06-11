package org.dyndns.stonehammer.pontus.OmxRemote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	public Client client;
	public String host;
	public int port;
	public final static String PREFS_NAME="Settings_file";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		client = new Client();
	}
	
	public void changeSettings(View view) {
		Intent i = new Intent(this, Settings.class);
		startActivity(i);
	}

	public void onClick(View view) {
		String control = new String();
		switch (view.getId()) {
		case R.id.pause:
			control = "pause\n";
			break;
		case R.id.conn:
			if(client.isConnected()) {
				client.disconnect();
				Button but = (Button)findViewById(R.id.conn);
				if(!client.isConnected()) {
					but.setText("Connect");
					but.setBackgroundResource(R.color.Green);
				}
			}
			else {
				getSettings();
				client.connect(host, port);
				Button but = (Button)findViewById(R.id.conn);
				if(client.isConnected()) {
					but.setText("Disconnect");
					but.setBackgroundResource(R.color.Red);
				}
					
			}
			control = "";
			break;
		case R.id.minus10m:
			control = "-10m\n";
			break;
		case R.id.minus30s:
			control = "-30s\n";
			break;
		case R.id.plus10m:
			control = "+10m\n";
			break;
		case R.id.plus30s:
			control = "+30s\n";
			break;
		case R.id.volDown:
			control = "vol-\n";
			break;
		case R.id.volUp:
			control = "vol+\n";
			break;
		default:
			control = "";
			break;
		}
		client.sendControl(control);

	}
	public void getSettings() {
		SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		host = sharedPrefs.getString("Host", "0.0.0.0");
		port = sharedPrefs.getInt("Port", 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
