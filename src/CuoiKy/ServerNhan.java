package CuoiKy;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerNhan extends Thread {
	private Socket socket;

	private String mabh;
	private String tenbh;
	private String casi;
	private String nhacsi;
	private String theloai;
	private String loi;
	private String namst;
	private String YeuCau = "";
	private String sms;

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

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

	public String getYeuCau() {
		return YeuCau;
	}

	public void setYeuCau(String yeuCau) {
		YeuCau = yeuCau;
	}

	public String getndTen() {
		return ndTen;
	}

	public void setndTen(String ndTen) {
		this.ndTen = ndTen;
	}


	private String ndTen = "";
	
	public String getNdMBH() {
		return ndMBH;
	}

	public void setNdMBH(String ndMBH) {
		this.ndMBH = ndMBH;
	}

	private String ndMBH = "";

	public ServerNhan(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		DataInputStream in = null;
		try {
			while (true) {
				in = new DataInputStream(socket.getInputStream());
				sms = in.readUTF();
				if (sms != null) {
					if (sms.contains("sqltruyvan")) {
						Tachchuoi();
					} else if (sms.contains("update")) {
						TachChuoiUpdate();
					}
				}
			}
		} catch (Exception e) {
			try {
				in.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	private void TachChuoiUpdate() {
		LamMoi();
		int size = sms.length();
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (sms.charAt(i) == ';') {
				index = index + 1;
				continue;
			}
			if (index == 0) {
				YeuCau = YeuCau + sms.charAt(i);
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
		theloai = "";
		loi = "";
		namst = "";
		YeuCau = "";

	}

	private void Tachchuoi() {
		ndTen = "";
		ndMBH = "";
		YeuCau = "";
		int size = sms.length();
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (sms.charAt(i) == ';') {
				index = index + 1;
				continue;
			}
			if (index == -1) {
				YeuCau = YeuCau + sms.charAt(i);
			}
			if (index == 0) {
				ndMBH = ndMBH + sms.charAt(i);
			}
			if (index == 1) {
				ndTen = ndTen + sms.charAt(i);
			}
		}
	}
}
