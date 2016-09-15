import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class ClienteArduino {
	
	public ClienteArduino(){
		// arguments supply message and hostname
				Socket s = null;
				try{
					int serverPort = 7896;
					s = new Socket("127.0.0.1", serverPort);    
					DataInputStream in = new DataInputStream( s.getInputStream());
					DataOutputStream out =new DataOutputStream( s.getOutputStream());
					Scanner scanner = new Scanner(System.in);
					
					String m = scanner.nextLine();
					out.writeUTF(m);
						
				}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
				}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
				}catch (IOException e){System.out.println("readline:"+e.getMessage());
				}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
	}
	
	public static void main (String args[]) {
		for(;true;)
			new ClienteArduino();
	}
}

