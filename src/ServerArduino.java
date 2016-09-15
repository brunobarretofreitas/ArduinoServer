import java.net.*;
import java.io.*;

public class ServerArduino {
	public static void main(String args[]) {
		try {
			System.out.println("Server ligado");

			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				System.out.println("Cliente conectado");
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		Arduino arduino = new Arduino();
		try { // an echo server
			while(clientSocket.isConnected() && !clientSocket.isClosed()){
				double temperatura = arduino.lerDados() / 8.0;
				temperatura = ((temperatura * 9) / 5) + 32;
				out.writeUTF(""+temperatura);
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 // read a line of data from the stream
		} catch (EOFException e) {
			arduino.fecharConexao();
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			arduino.fecharConexao();
			System.out.println("readline:" + e.getMessage());
		} finally {
			try {
				System.out.println("fechou o cliente");
				arduino.fecharConexao();
				clientSocket.close();
			} catch (IOException e) {
				/* close failed */}
		}
	}
}