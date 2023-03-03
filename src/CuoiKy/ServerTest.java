package CuoiKy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ServerTest {
	Vector<Object> vListSocket = new Vector<Object>();
	public ServerTest() {
		try {
			ServerSocket server = new ServerSocket(8787);
			
			Thread th1 = new Thread() { // thread de doc thong tin
				@Override 
				public void run() {
					while(true) {
						try {
							Socket socket = server.accept();
							System.out.println("dong y");
							ServerDoc svd = new ServerDoc(socket);
							svd.start();
							vListSocket.add(socket);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			};
			
			Thread th2 = new Thread() { // Thread de gui thong tin
				@Override
				public void run() {
					
					while(true) {
						try {
							Scanner scan = new Scanner(System.in);
							System.out.println("goi thong tin den client");
							String gui = scan.nextLine();
							int size = vListSocket.size();
							for(int i = 0; i < size; i ++) {
								Socket socket = (Socket) vListSocket.get(i);
								ServerViet svv = new ServerViet(socket, gui);
								svv.start();
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			};
			th1.start();
			th2.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void main(String[] args) {
		ServerTest server =  new ServerTest();
	}
}

//class doc
class ServerDoc extends Thread{
	Socket socket;
	public ServerDoc(Socket socket) {
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


//class viet
class ServerViet extends Thread{
	Socket socket;
	String gui;
	public ServerViet(Socket socket, String gui) {
		
		this.socket = socket;
		this.gui = gui;
	}
	@Override
	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("server : "+ gui);
			out.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}


