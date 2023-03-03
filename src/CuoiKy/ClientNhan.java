package CuoiKy;

import java.io.DataInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientNhan extends Thread {
	Socket socket;

	private String mabh = "";
	private String tenbh = "";
	private String casi = "";
	private String nhacsi = "";
	private String theloai = "";
	private String loi = "";
	private String namst = "";

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getMabh() {
		return mabh;
	}

	public void setMabh(String mabh) {
		this.mabh = mabh;
	}

	public String getTenbh() {
		return tenbh;
	}

	public void setTenbh(String tenbh) {
		this.tenbh = tenbh;
	}

	public String getCasi() {
		return casi;
	}

	public void setCasi(String casi) {
		this.casi = casi;
	}

	public String getNhacsi() {
		return nhacsi;
	}

	public void setNhacsi(String nhacsi) {
		this.nhacsi = nhacsi;
	}

	public String getTheloai() {
		return theloai;
	}

	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}

	public String getLoi() {
		return loi;
	}

	public void setLoi(String loi) {
		this.loi = loi;
	}

	public String getNamst() {
		return namst;
	}

	public void setNamst(String namst) {
		this.namst = namst;
	}

	private String ndnhan = "";

	public String getNdnhan() {
		return ndnhan;
	}

	public void setNdnhan(String ndnhan) {
		this.ndnhan = ndnhan;
	}

	public ClientNhan(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		DataInputStream in = null;
		while (true) { 
			try {
				in = new DataInputStream(socket.getInputStream());
				String sms = in.readUTF();
				ndnhan = sms;
				if (ndnhan != "") {
					if (sms.equalsIgnoreCase("exit")) {
						System.out.println("tắt");
						in.close();
					} else {
						LamMoi();
						TachChuoi(sms);
					}
				}
				Thread.sleep(1000);
				ndnhan = "";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void TachChuoi(String sms) {
		int size = sms.length();
		int index = 1;
		for (int i = 0; i < size; i++) {
			if (sms.charAt(i) == ';') {
				index = index + 1;
				continue;
			}
			if (index == 1) {
				mabh = mabh + sms.charAt(i);
			}
			if (index == 2) {
				tenbh = tenbh + sms.charAt(i);
			}
			if (index == 3) {
				casi = casi + sms.charAt(i);
			}
			if (index == 4) {
				nhacsi = nhacsi + sms.charAt(i);
			}
			if (index == 5) {
				loi = loi + sms.charAt(i);
			}
			if (index == 6) {
				theloai = theloai + sms.charAt(i);
			}
			if (index == 7) {
				namst = namst + sms.charAt(i);
			}
		}
	}

	private void LamMoi() {
		mabh = "";
		tenbh = "";
		casi = "";
		nhacsi = "";
		loi = "";
		theloai = "";
		namst = "";

	}
}
