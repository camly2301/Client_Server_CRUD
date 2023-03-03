package CuoiKy;

import java.io.DataOutputStream;
import java.net.Socket;

public class ClientGui extends Thread{
	Socket socket;
	String nd ;

	public ClientGui(Socket socket, String nd) {
		this.socket = socket;
		this.nd = nd;
	}
	@Override
	public void run() {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(nd);
			out.flush();
		} catch (Exception e) {
			try {
				out.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
