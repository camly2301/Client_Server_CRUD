package CuoiKy;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TimKiem extends JFrame{

	private JPanel panel1, panel2, panel3;
	private JButton btnqlai;
	private JLabel lbltieude; 			
	private Container cont;
	private JScrollPane heee;
	private JTable table1;
	public TimKiem(String lc) {
		Vector vData = null;
		Vector vtitle = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info_song", "root",
					"212888964");
			Statement stm = conn.createStatement();
			ResultSet rst = stm.executeQuery("SELECT MABH AS'Mã bài hát', TENBAIHAT AS'Tên bài hát', CASI AS'Ca sĩ', NHACSI AS'Nhạc sĩ', MODAUBH AS'Mở đầu bài hát', THELOAI AS'Thể loại',NAMST AS 'Năm sáng tác'  \r\n"
					+ " FROM info_song.baihat where MABH like N'" + lc + "' "
					+ "or  TENBAIHAT like N'%" + lc + "%'" + "or  CASI like N'%" + lc + "%'"
					+ "or  NHACSI like N'%" + lc + "%'" + "or  MODAUBH like N'%" + lc + "%'"  + "or  THELOAI like N'%" + lc + "%'" + "or NAMST like N'%" + lc + "%'");
			ResultSetMetaData rstmeta = rst.getMetaData();
			int col_num = rstmeta.getColumnCount();
			vtitle = new Vector(col_num);
			for (int i = 1; i <= col_num; i++) {
				vtitle.add(rstmeta.getColumnLabel(i));
			}
			vData = new Vector(10, 10);
			while (rst.next()) {
				Vector row = new Vector(col_num);
				for (int i = 1; i <= col_num; i++) {
					row.add(rst.getString(i));
				}
				vData.add(row);
				
			}
			rst.close();//xóa bảng đã tìm kiếm
			stm.close();// xóa dữ liệu đã tìm kiếm
			conn.close();//đóng kết nối
			if(vData.size() <= 0 ) {
				JOptionPane.showMessageDialog(null,"KHÔNG CÓ THÔNG TIN BÀI HÁT ");
			}
			else {
				cont = this.getContentPane();
				lbltieude = new JLabel("THONG TIN BAI HAT");
				panel1 = new JPanel();
				panel2 = new JPanel();
				panel3 = new JPanel();
				btnqlai = new JButton("Quay lai");
				table1 = new JTable(vData, vtitle);
				heee = new JScrollPane(table1);
				
				cont.add(panel1);
				cont.add(panel3);
				cont.add(panel2);
				
				panel3.add(heee);
				panel1.add(lbltieude);
				panel2.add(btnqlai);
				
				this.setTitle("Thông tin");
				this.setSize(800, 400);
				this.setContentPane(heee);
				this.setLocationRelativeTo(null);
		
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				this.setVisible(true);
			}
		} catch (Exception e) {

			System.out.println(e);
		}

}
//:))	
}
