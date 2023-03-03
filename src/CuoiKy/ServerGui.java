package CuoiKy;

import java.io.DataOutputStream;
import java.net.Socket;

public class ServerGui extends Thread{
	Socket socket;
	String gui;
	public ServerGui(Socket socket, String gui) {
		this.socket = socket;
		this.gui = gui; 
	}
	@Override
	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(gui);
			out.flush();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
