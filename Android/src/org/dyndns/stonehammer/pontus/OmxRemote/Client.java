package org.dyndns.stonehammer.pontus.OmxRemote;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.util.Log;


public class Client {

	private Socket myClient = null;
	private BufferedReader inputStream = null;
	private DataOutputStream outputStream = null;
	private final String host = "192.168.1.143";
	private final int port = 1337;

	public Client() {


		try {
			myClient = new Socket(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Start");
		try {
			inputStream = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			outputStream = new DataOutputStream(myClient.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void sendControl(String control) {
		try {
			outputStream.writeBytes(control);
			Log.d("Mymsg", control);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		
		try {
			inputStream.close();
			outputStream.close();
			myClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

}
