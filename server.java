import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements Runnable {

	Socket clientSocket;

	public server(Socket socket) {
		this.clientSocket = socket;
	}

	public static void main(String[] args) {
		ServerSocket myService = null;
		boolean running = true;
		System.out.println("--- OMXRemote - Server ---");
		try {
			myService = new ServerSocket(1337);
			System.out.println("Listening...");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (running) {

			try {
				Socket sock = myService.accept();

				System.out.println("Connected to : " + sock.getInetAddress());
				new Thread(new server(sock)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			myService.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		BufferedReader input = null;
		DataOutputStream output = null;
		String recv = new String(), send = new String(), control = new String();

		try {
			input = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			output = new DataOutputStream(clientSocket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while ((recv = input.readLine()) != null) {
				if (recv.equals("pause")) {
					control = "p";
				} else if (recv.equals("+10m")) {
					control = "\033[A";
				} else if (recv.equals("-10m")) {
					control = "\033[B";
				} else if (recv.equals("+30s")) {
					control = "\033[C";
				} else if (recv.equals("-30s")) {
					control = "\033[D";
				} else if (recv.equals("vol+")) {
					control = "+";
				} else if (recv.equals("vol-")) {
					control = "-";
				} else if (recv.equals("quit")) {
					control = "q";
				} else {
					control = "";
				}
				try {
					Process p = Runtime.getRuntime().exec(
							"./omx_control.sh " + control);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			input.close();
			output.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

