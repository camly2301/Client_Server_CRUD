package CuoiKy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
	 ClientTest() {

		try {
			Socket socket = new Socket("localhost", 8787);
			ClientViet clv = new ClientViet(socket); 
			ClientDoc cld = new ClientDoc(socket);
			clv.start();
			cld.start();

		} catch (Exception e) {
			
		}

}
	public static void main(String[] args) {
		ClientTest client = new ClientTest();
	}
}

class ClientDoc extends Thread {
	Socket socket ;
	public ClientDoc(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		while(true) {
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				String doc = in.readUTF();
				System.out.println(doc);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

 class ClientViet extends Thread{
	 Socket socket;
	 public ClientViet(Socket socket) {
		 this.socket = socket;
	 }
	 @Override
	public void run() {
		 Scanner scan = new Scanner(System.in);
			while(true) {
				try {
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					System.out.println("goi thong tin den server");
					String gui = scan.nextLine();
					out.writeUTF("client : "+gui);
					out.flush();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	}
	
 }

