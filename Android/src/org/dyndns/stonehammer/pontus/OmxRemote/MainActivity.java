package org.dyndns.stonehammer.pontus.OmxRemote;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	public Client client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		client = new Client();
	}

	public void onClick(View view) {
		String control = new String();
		switch (view.getId()) {
		case R.id.pause:
			control = "pause\n";
			break;
		case R.id.disconnect:
			client.disconnect();
			System.exit(0);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
