package org.dyndns.stonehammer.pontus.OmxRemote;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


public class Client {

	private Socket myClient = null;
	private BufferedReader inputStream = null;
	private DataOutputStream outputStream = null;
	private boolean connected = false;

	public Client() {
	}
	
	public void sendControl(String control) {
		if(!connected)
			return;
		try {
			outputStream.writeBytes(control);
			Log.d("Mymsg", control);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, int port) {
		
		try {
			myClient = new Socket(host, port);
		} catch (Exception e) {
			connected = false;
			return;
		}

		System.out.println("Start");
		try {
			inputStream = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
		} catch (IOException e) {
			connected = false;
			return;
		}

		try {
			outputStream = new DataOutputStream(myClient.getOutputStream());
		} catch (IOException e) {
			connected = false;
			return;
		}
		connected = true;
	}
	public void disconnect() {
		
		try {
			inputStream.close();
			outputStream.close();
			myClient.close();
		} catch (Exception e) {
		}		
		connected = false;
		
	}
	
	public boolean isConnected() {
		return connected;
	}

}
