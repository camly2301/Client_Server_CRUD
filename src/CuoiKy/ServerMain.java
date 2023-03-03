package CuoiKy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class ServerMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblMuBi, lblNmSngTc, lblNewLabel_1, lblNewLabel_3, lbltrangthai, lblNmSngTc_1;
	private JTable table;
	private JTextField tftbh, tfcasi, tftheloai, tfloi, tfnhacsi, tfport, tfmbh, tftimkiem, tfnamst, tftrangthai;
	private JButton btnBat, btnTat, btnNgatkn, btngui, btnxemycs, btndongy;
	private Statement stm;
	private Connection conn;
	private PreparedStatement prst;
	private ResultSet rst;
	private Vector vData = new Vector();
	private Vector vTitle = new Vector();
	private DefaultTableModel model, modelsocket;
	private int duochaykhong;
	private int selectedRow = -1;
	private String mabh1;
	private Vector vListSocket = new Vector();
	private Vector vTitlelistsocket = new Vector();
	private int ketnoi = 0;
	private ServerSocket server;
	private JTable table_1;
	private String ndMBH = "";
	private String ndTBH = "";
	private String Gui = "";
	private String mabh = "";
	private String tenbh = "";
	private String casi = "";
	private String nhacsi = "";
	private String loi = ""; 
	private String theloai = "";
	private String namst = "";
	private Vector vCapNhat = new Vector(); // vector bóc tách dữ liệu dùng để đẩy lên tf
	private int vitrisua = -1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMain frame = new ServerMain();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerMain() throws IOException {
		KetNoiCSDL();
		NapDuLieuVaoBang();
		NapUser(); 
		// phần giao diện và bắt sự kiện các nút
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1218, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã bài hát:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(734, 65, 101, 29);
		contentPane.add(lblNewLabel);

		JLabel lblTnBiHt = new JLabel("Tên bài hát:");
		lblTnBiHt.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnBiHt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnBiHt.setBounds(734, 104, 101, 29);
		contentPane.add(lblTnBiHt);

		JLabel lblCaS = new JLabel("Ca sĩ:");
		lblCaS.setVerticalAlignment(SwingConstants.TOP);
		lblCaS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCaS.setBounds(734, 156, 101, 25);
		contentPane.add(lblCaS);

		JLabel lblNhcS = new JLabel("Nhạc sĩ:");
		lblNhcS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhcS.setBounds(734, 191, 101, 29);
		contentPane.add(lblNhcS);

		lblMuBi = new JLabel("Thể loại :");
		lblMuBi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMuBi.setBounds(729, 269, 101, 29);
		contentPane.add(lblMuBi);

		lblNmSngTc = new JLabel("Mở đầu bài hát:");
		lblNmSngTc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmSngTc.setBounds(729, 230, 101, 29);
		contentPane.add(lblNmSngTc);

		// nút thêm
		JButton btnthem = new JButton("THÊM");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Them();
			}

		});
		btnthem.setBackground(new Color(204, 204, 204));
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBounds(1078, 69, 116, 29);
		contentPane.add(btnthem);

		// nút sửa
		JButton btnsua = new JButton("SỬA");
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sua(tfmbh.getText());
			}

		});
		btnsua.setBackground(new Color(204, 204, 204));
		btnsua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnsua.setBounds(1078, 118, 116, 30);
		contentPane.add(btnsua);

		// nút xóa
		JButton btnxoa = new JButton("XÓA");
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// click chuột để chọn hàng muốn xóa
					selectedRow = table.getSelectedRow();
					// lấy nội dung hàng đã chọn
					Vector st = (Vector) vData.elementAt(selectedRow);
					PreparedStatement pstm = conn.prepareStatement("delete from baihat where MABH = ?");
					conn.setAutoCommit(false);
					pstm.setString(1, (String) st.elementAt(0));
					pstm.executeUpdate();
					conn.commit();
					vData.remove(selectedRow);
					model.fireTableDataChanged();
				} catch (Exception e2) {

				}
			}
		});
		btnxoa.setBackground(new Color(204, 204, 204));
		btnxoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxoa.setBounds(1078, 168, 116, 30);
		contentPane.add(btnxoa);

		// nút reset làm mới 
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapDuLieuVaoBang();
				model.fireTableDataChanged();
				tfmbh.setText("");
				tftbh.setText("");
				tfcasi.setText("");
				tfnhacsi.setText("");
				tfloi.setText("");
				tftheloai.setText("");
				tfnamst.setText("");
			}
		});
		btnReset.setBackground(new Color(204, 204, 204));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(1078, 369, 116, 30);
		contentPane.add(btnReset);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 424, 1184, 285);
		contentPane.add(scrollPane);

		model = new DefaultTableModel(vData, vTitle);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		tfmbh = new JTextField();
		tfmbh.setBounds(845, 69, 205, 25);
		contentPane.add(tfmbh);
		tfmbh.setColumns(10);

		tftbh = new JTextField();
		tftbh.setColumns(10);
		tftbh.setBounds(845, 108, 205, 25);
		contentPane.add(tftbh);

		tfcasi = new JTextField();
		tfcasi.setColumns(10);
		tfcasi.setBounds(845, 153, 205, 25);
		contentPane.add(tfcasi);

		tftheloai = new JTextField();
		tftheloai.setColumns(10);
		tftheloai.setBounds(845, 273, 205, 25);
		contentPane.add(tftheloai);

		tfloi = new JTextField();
		tfloi.setColumns(10);
		tfloi.setBounds(845, 234, 205, 25);
		contentPane.add(tfloi);

		tfnhacsi = new JTextField();
		tfnhacsi.setColumns(10);
		tfnhacsi.setBounds(845, 194, 205, 25);
		contentPane.add(tfnhacsi);

		lblNewLabel_1 = new JLabel("Cổng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 70, 49, 19);
		contentPane.add(lblNewLabel_1);

		tfport = new JTextField();
		tfport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfport.setText("8788");
		tfport.setBounds(83, 65, 96, 25);
		contentPane.add(tfport);
		tfport.setColumns(10);

		tftimkiem = new JTextField();
		tftimkiem.setBounds(845, 372, 205, 29);
		contentPane.add(tftimkiem);
		tftimkiem.setColumns(10);

		// nút bật server 
		btnBat = new JButton("BẬT");
		btnBat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ketnoi == 0) {
					Thread th1 = new Thread() {
						public void run() {
							try {
								tftrangthai.setText("Bật");
								ketnoi = 1;
								// chuyển trạng thái thành Bật rồi gọi hàm BatServer để khởi động
								BatServer(Integer.parseInt(tfport.getText()), 0); 
						//		System.out.println("kết nối thành công");
							} catch (Exception e2) {
								
							}
						}

					};
					th1.start();
				}
			}
		});
		btnBat.setForeground(new Color(102, 153, 51));
		btnBat.setBackground(new Color(204, 204, 204));
		btnBat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBat.setBounds(21, 322, 89, 25);
		contentPane.add(btnBat);

		// nút tắt 
		btnTat = new JButton("TẮT");
		btnTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ketnoi == 1) {
					try {
						tftrangthai.setText("Tắt");
						server.close();
						//tắt server , socket đang kết nối cũng sẽ bị ngắt
						BatServer(Integer.parseInt(tfport.getText().toString()), 1);
						ketnoi = 0;
						int size = vListSocket.size();
						for (int i = 0; i < size; i++) {
							Vector row = (Vector) vListSocket.get(i); // lấy phần tử thứ i vListSocket bỏ vào row
							Socket socket = (Socket) row.get(0);
							ServerGui svg = new ServerGui(socket, "exit"); // gởi thông báo cho socket là server đã tắt 
							svg.start();
						}
						vListSocket.clear(); // xóa các socket có đã trên bảng 
						modelsocket.fireTableDataChanged(); // reload lại bảng
					} catch (Exception e2) {
						
					}
				}
			}
		});
		btnTat.setBackground(new Color(204, 204, 204));
		btnTat.setForeground(new Color(255, 0, 0));
		btnTat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTat.setBounds(131, 322, 89, 25);
		contentPane.add(btnTat);

		// nút ngắt kết nối 
		btnNgatkn = new JButton("NGẮT KẾT NỐI");
		btnNgatkn.setBackground(new Color(204, 204, 204));
		btnNgatkn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedListSocket = table_1.getSelectedRow(); // chọn socket muốn ngắt
					Vector row = (Vector) vListSocket.get(selectedListSocket); // lấy thông tin socket đó dùng phương thức get() của vector
					Socket socket = (Socket) row.get(0);
					ServerGui svg = new ServerGui(socket, "exit"); // gửi thông báo cho socket đó là server đã ngắt kết nối 
					svg.start();
					vListSocket.remove(selectedListSocket); // xóa socket đó ra khỏi modelsocket
					JOptionPane.showMessageDialog(contentPane, "Đã ngắt kết nối");
					modelsocket.fireTableDataChanged();
					socket.close(); // ngắt kết nối socket
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNgatkn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNgatkn.setBounds(423, 322, 158, 25);
		contentPane.add(btnNgatkn);

		// nút gửi thông tin 
		btngui = new JButton("GỬI THÔNG TIN");
		btngui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// thread để gởi thông tin 
				Thread th1 = new Thread() {
					@Override
					public void run() {
						int selectedListSocket = table_1.getSelectedRow();
						Vector row = (Vector) vListSocket.get(selectedListSocket);
						Socket socket = (Socket) row.get(0); // lấy socket (ở col 0 là socket)
						NapThongTin(selectedListSocket, socket);
						ServerGui svg = new ServerGui(socket, Gui);
						svg.start();
					}
				};
				th1.start();
			}

		});
		btngui.setBackground(new Color(204, 204, 204));
		btngui.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btngui.setBounds(242, 322, 158, 25);
		contentPane.add(btngui);

		// nút xem yêu cầu sửa
		btnxemycs = new JButton("XEM YÊU CẦU SỬA");
		btnxemycs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowSocket = table_1.getSelectedRow();
				vitrisua = selectRowSocket;
				Vector row = (Vector) vListSocket.get(selectRowSocket);
				Socket socket = (Socket) row.get(0);
				DuaThongTinUpdateLenTextField(socket);
			}

			private void DuaThongTinUpdateLenTextField(Socket socket) {
				int size = vCapNhat.size();
				for (int i = 0; i < size; i++) {
					Vector row = (Vector) vCapNhat.get(i); // lấy giá trị ở phần tử i của vCapNhat 
					Socket socketUp = (Socket) row.get(0);
					System.out.println(socketUp);
					if (socket == socketUp) {
						// đưa lên TextField
						tfmbh.setText(row.get(1).toString());
						tftbh.setText(row.get(2).toString());
						tfcasi.setText(row.get(3).toString());
						tfnhacsi.setText(row.get(4).toString());
						tftheloai.setText(row.get(5).toString());
						tfloi.setText(row.get(6).toString());
						tfnamst.setText(row.get(7).toString());
					}
				}

			}
		});
		btnxemycs.setBackground(new Color(204, 204, 204));
		btnxemycs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxemycs.setBounds(21, 363, 158, 25);
		contentPane.add(btnxemycs);

		lbltrangthai = new JLabel("Trạng thái:");
		lbltrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltrangthai.setBounds(224, 65, 79, 29);
		contentPane.add(lbltrangthai);

		tftrangthai = new JTextField();
		tftrangthai.setHorizontalAlignment(SwingConstants.CENTER);
		tftrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tftrangthai.setEditable(false);
		tftrangthai.setText("Tắt");
		tftrangthai.setBounds(297, 64, 42, 31);
		contentPane.add(tftrangthai);
		tftrangthai.setColumns(10);
		
		// nút đồng ý sửa 
		btndongy = new JButton("ĐỒNG Ý SỬA");
		btndongy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vitrisua >= 0) {
					int indexSelectRow = vitrisua;
					Vector row = (Vector) vListSocket.get(indexSelectRow);
					Socket socket = (Socket) row.get(0);
					DongYSua(row, socket, row.get(1).toString());

				}
			}

			private void DongYSua(Vector row, Socket socket, String lenh) {
				try {
					if (lenh.equalsIgnoreCase("Cập nhật")) {
						prst = conn.prepareStatement(
								"update baihat set MABH = ?, TENBAIHAT = ?, CASI = ? , NHACSI = ? , MODAUBH = ? , THELOAI = ? , NAMST = ? where  MABH = ?");
						prst.setString(1, tfmbh.getText());
						prst.setString(2, tftbh.getText());
						prst.setString(3, tfcasi.getText());
						prst.setString(4, tfnhacsi.getText());
						prst.setString(5, tfloi.getText());
						prst.setString(6, tftheloai.getText());
						prst.setString(7, tfnamst.getText());
						prst.setString(8, row.get(3).toString().toUpperCase());
						int thanhcong = prst.executeUpdate(); // thực hiện lệnh sql
						if (thanhcong > 0) { 
							JOptionPane.showMessageDialog(contentPane, "Đã sửa");
							CapNhatTrangThai(socket, "Đã cập nhật");
							prst = conn.prepareStatement("select * from baihat where MABH = ?");
							prst.setString(1, tfmbh.getText().toString());
							ResultSet rst = prst.executeQuery();
							ResultSetMetaData rstmt = rst.getMetaData();
							int col = rstmt.getColumnCount(); // lấy số cột của bảng 
							String nd = ""; 
							while (rst.next()) { // đưa dữ liệu từng cột vào chuỗi nd 
								for (int i = 1; i <= col; i++) {
									if (i == col) {
										nd = nd + rst.getString(i);
									} else {
										nd = nd + rst.getString(i) + ';';
									}
								}
							}
							ServerGui svg = new ServerGui(socket, "Đã sửa"); // gửi cho client 
							svg.start();
							NapDuLieuVaoBang();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		btndongy.setBackground(new Color(204, 204, 204));
		btndongy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btndongy.setBounds(197, 363, 158, 25);
		contentPane.add(btndongy);

		lblNewLabel_3 = new JLabel("BÀI HÁT KARAOKE");
		lblNewLabel_3.setForeground(new Color(255, 102, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel_3.setBounds(437, 10, 287, 47);
		contentPane.add(lblNewLabel_3);

		lblNmSngTc_1 = new JLabel("Năm sáng tác:");
		lblNmSngTc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmSngTc_1.setBounds(734, 308, 101, 29);
		contentPane.add(lblNmSngTc_1); 

		tfnamst = new JTextField();
		tfnamst.setColumns(10);
		tfnamst.setBounds(845, 308, 205, 25);
		contentPane.add(tfnamst);

		// nút tìm kiếm
		JButton btnTim = new JButton("TÌM KIẾM");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TimKiem(tftimkiem.getText());
			}

		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTim.setBackground(new Color(204, 204, 204));
		btnTim.setBounds(714, 371, 116, 30);
		contentPane.add(btnTim);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 118, 666, 170);
		contentPane.add(scrollPane_1);

		modelsocket = new DefaultTableModel(vListSocket, vTitlelistsocket);
		table_1 = new JTable(modelsocket);
		scrollPane_1.setViewportView(table_1);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Vector row = (Vector) vData.get(table.getSelectedRow());
				tfmbh.setText(row.elementAt(0).toString());
				tftbh.setText(row.elementAt(1).toString());
				tfcasi.setText(row.elementAt(2).toString());
				tfnhacsi.setText(row.elementAt(3).toString());
				tfloi.setText(row.elementAt(4).toString());
				tftheloai.setText(row.elementAt(5).toString());
				tfnamst.setText(row.elementAt(6).toString());
			}
		});
// thread để load lại bảng cập nhật các socket modelsocket
		Thread th1 = new Thread() {
			@Override
			public void run() {
				while (true) {
					modelsocket.fireTableDataChanged();
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};
		th1.start();
	}

	private void NapDuLieuVaoBang() {
		try {
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery(
					"SELECT MABH AS'Mã bài hát', TENBAIHAT AS'Tên bài hát', CASI AS'Ca sĩ', NHACSI AS'Nhạc sĩ', MODAUBH AS'Mở đầu bài hát', THELOAI AS'Thể loại', NAMST AS 'Năm sáng tác'  \r\n"
							+ " FROM info_song.baihat;");
			// tạo resultsetmetadata để lấy số cột của resultset
			ResultSetMetaData rstmeta = rst.getMetaData();
			int col_num = rstmeta.getColumnCount();
			for (int i = 1; i <= col_num; i++) {
				vTitle.add(rstmeta.getColumnLabel(i));
			}
			while (rst.next()) {
				Vector row = new Vector(col_num);
				for (int i = 1; i <= col_num; i++) {
					row.add(rst.getString(i));

				}
				vData.add(row);

			}
			rst.close();
			model.fireTableDataChanged();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}

	}

	private void KetNoiCSDL() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info_song", "root", "212888964");
			stm = conn.createStatement();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void Sua(String mabh) {
		try {
			this.mabh1 = mabh;
			PreparedStatement pstm = conn.prepareStatement(
					"update baihat set MABH = ? ,TENBAIHAT = ? ,CASI = ?,NHACSI = ?,MODAUBH = ?, THELOAI = ?, NAMST = ? where  MABH = ?");
			conn.setAutoCommit(false);
			pstm.setString(1, tfmbh.getText());
			pstm.setString(2, tftbh.getText());
			pstm.setString(3, tfcasi.getText());
			pstm.setString(4, tfnhacsi.getText());
			pstm.setString(5, tfloi.getText());
			pstm.setString(6, tftheloai.getText());
			pstm.setString(7, tfnamst.getText());
			pstm.setString(8, this.mabh1);
			duochaykhong = pstm.executeUpdate();
			conn.commit();
		} catch (Exception e) {

		}
		if (duochaykhong > 0) {
			JOptionPane.showMessageDialog(btnNgatkn, "Sửa thành công");
		} else {
			JOptionPane.showMessageDialog(btnNgatkn, "Sửa không thành công");
		}

	}

	private void Them() {
		try {
			PreparedStatement pstm = conn.prepareStatement(
					"insert into baihat(MABH,TENBAIHAT ,CASI,NHACSI ,MODAUBH, THELOAI, NAMST) values (?,?,?,?,?,?,?)");
			conn.setAutoCommit(false);// chặn gửi lệnh đi
			pstm.setString(1, tfmbh.getText());
			pstm.setString(2, tftbh.getText());
			pstm.setString(3, tfcasi.getText());
			pstm.setString(4, tfnhacsi.getText());
			pstm.setString(5, tfloi.getText());
			pstm.setString(6, tftheloai.getText());
			pstm.setString(7, tfnamst.getText());
			duochaykhong = pstm.executeUpdate();
			conn.commit();// gửi lệnh đi ,đến đây lệnh mới được thực hiện
		} catch (Exception e2) {
			// TODO: handle exception
		}
		if (duochaykhong > 0) {
			JOptionPane.showMessageDialog(btnNgatkn, "Thêm thành công");
		}

		else {
			JOptionPane.showMessageDialog(btnNgatkn, "Trùng ID");
		}

	}

	private void BatServer(int port, int i) {
		try {
			// i == 0 server đang bật -> không chạy, i == 1 server chưa bật -> server sẽ bật
			if (i == 1) {
				server.close();
			} else {
				server = new ServerSocket(Integer.parseInt(tfport.getText())); // lấy địa chỉ cổng : 8788
				while (true) {
					if (i == 1) {
						break;
					}
					
					Socket socket = server.accept();
					System.out.println("dong y");
					ServerNhan svn = new ServerNhan(socket);
					Vector row = new Vector();
					// add các socket kết nối vào 
					row.add(socket);
					row.add(1, "");
					row.add(2, "Đang kết nối...");
					row.add(3, "");
					row.add(4, "");

					Thread th1 = new Thread() {
						@Override
						public void run() {
							while (true) {
								if (svn.getSms() != null) { // sms từ ClientGui 
									System.out.println("sms = " + svn.getSms());
									if (svn.getSms().equals("exit")) {
										CapNhatTrangThai(socket, "Đã thoát");
										svn.setSms(null);
									} else {
										if (svn.getSms().contains("sqltruyvan")) {
											ndMBH = svn.getNdMBH();
											ndTBH = svn.getndTen();
											row.set(1, "Truy vấn");
											row.set(3, svn.getNdMBH());
											TruyVan(socket);
											svn.setSms(null);
											System.out.println(svn.getSms());
										} else if (svn.getSms().contains("update")) {
											System.out.println(svn.getSms());
											Clear();
											mabh = svn.getMabh();
											tenbh = svn.getTenbh();
											casi = svn.getCasi();
											nhacsi = svn.getNhacsi();
											theloai = svn.getTheloai();
											loi = svn.getLoi();
											namst = svn.getNamst();
											svn.setSms(null);
											CapnhatvaovCapNhat(socket, mabh, tenbh, casi, nhacsi, theloai, loi, namst);
											CapNhatTrangThai(socket, "Cập nhật");

										}

									}
								}

								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}

					};
					svn.start();
					th1.start();
					System.out.println(svn.getYeuCau());
					vListSocket.add(row);
					modelsocket.fireTableDataChanged();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void NapUser() {
		try {
			/// add title cho modelsocket
			vTitlelistsocket.add("Socket đang kết nối");
			vTitlelistsocket.add("Yêu cầu");
			vTitlelistsocket.add("Trạng thái");
			vTitlelistsocket.add("Mã bài hát");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void CapNhatTrangThai(Socket socket, String lenh) {
		int size = vListSocket.size();
		if (lenh.equalsIgnoreCase("Đã thoát")) {
			for (int i = 0; i < size; i++) {
				Vector row = (Vector) vListSocket.get(i);
				Socket socketList = (Socket) row.get(0);
				if (socket == socketList) {
					vListSocket.remove(i);
					JOptionPane.showMessageDialog(contentPane, "Socket =" + socket + "Đã Thoát", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
				modelsocket.fireTableDataChanged();
			}
		} 
		else if (lenh.equalsIgnoreCase("Cập nhật")) {
			for (int i = 0; i < size; i++) {
				Vector row = (Vector) vListSocket.get(i);
				Socket socketList = (Socket) row.get(0);
				if (socket == socketList) {
					row.set(1, lenh);
					row.set(2,"Tồn tại");
				}
				vListSocket.set(i, row);
			}
		} 

		else {
			for (int i = 0; i < size; i++) {
				Vector row = (Vector) vListSocket.get(i);
				Socket socketList = (Socket) row.get(0);
				if (socket == socketList) {
					row.set(2, lenh);
					vListSocket.set(i, row);
				}
			}
		}
		modelsocket.fireTableDataChanged();
	}

	private void TruyVan(Socket socket) {
		// TODO Auto-generated method stub

		try {
			int trunglap = 0;
			int vitritrung = -1;
			int kiemtraketqua = 0;
			PreparedStatement prst1 = conn.prepareStatement("select * from baihat where MABH LIKE ? AND TENBAIHAT LIKE ? ");
			prst1.setString(1, ndMBH);
			prst1.setString(2, ndTBH);
			ResultSet rst = prst1.executeQuery();
			ResultSetMetaData rstm = rst.getMetaData();
			int col = rstm.getColumnCount();
			int size = vCapNhat.size();
			// nếu chưa có socket nào hết 
			if (size == 0) {
				Vector row = new Vector();
				row.add(socket);
				while (rst.next()) { // đến những nội dung của cột != null
					kiemtraketqua = 1;
					for (int i = 1; i <= col; i++) {
						row.add(rst.getString(i));
					}
					vCapNhat.add(row);
				}
				//nếu đã có socket và sẽ kiểm tra xem socket này có tồn tại chưa
			} else {
				for (int i = 0; i < size; i++) {
					Vector row = (Vector) vCapNhat.get(i);
					Socket socketCheck = (Socket) row.get(0);
					if (socketCheck == socket) {
						trunglap = 1;
						vitritrung = i;
					}
				}
				// nếu không có trùng lặp thì thêm mới 
				if (trunglap == 0) {
					Vector row = new Vector();
					row.add(socket);
					while (rst.next()) {
						kiemtraketqua = 1;
						for (int i = 1; i <= col; i++) {
							row.add(rst.getString(i));
						}
						vCapNhat.add(row);
					}
					//nếu đã có socket thì chỉ cần thay thế nội dung là được
				} else if (trunglap == 1) {
					Vector row = new Vector();
					row.add(socket);
					while (rst.next()) {
						kiemtraketqua = 1;
						for (int i = 1; i <= col; i++) {
							row.add(rst.getString(i));
						}
						vCapNhat.set(vitritrung, row);
					}
				}

			}
			if (kiemtraketqua == 1) {
				CapNhatTrangThai(socket, "Tồn tại");
				ServerGui qtg = new ServerGui(socket, "Tồn tại");
				qtg.start();
			} else {
				CapNhatTrangThai(socket, "Không tồn tại");
				ServerGui qtg = new ServerGui(socket, "Không tồn tại");
				qtg.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void Clear() {
		mabh = "";
		tenbh = "";
		casi = "";
		nhacsi = "";
		theloai = "";
		loi = "";
		namst = "";

	}
// đưa thông tin vào vCapNhat 
	private void CapnhatvaovCapNhat(Socket socket, String mabh, String tenbh, String casi, String nhacsi,
			String theloai, String loi, String namst) {
		int size = vCapNhat.size();
		if (size == 0) { // add dữ liệu vào vCapNhat 
			Vector row = new Vector(); 
			row.add(socket);
			row.add(mabh);
			row.add(tenbh);
			row.add(casi);
			row.add(nhacsi);
			row.add(theloai);
			row.add(loi);
			row.add(namst);
			vCapNhat.add(row);
		} else { // có lặp thì xem thằng nào lặp 
			int lap = 0;
			int laptai = -1;
			for (int i = 0; i < size; i++) {
				Vector row = (Vector) vCapNhat.get(i);
				Socket socketUp = (Socket) row.get(0);
				if (socket == socketUp) {
					lap = 1;
					laptai = i;
				}
			}
			if (lap == 1) {
				Vector row = new Vector();
				row.add(socket);
				row.add(mabh);
				row.add(tenbh);
				row.add(casi);
				row.add(nhacsi);
				row.add(theloai);
				row.add(loi);
				row.add(namst);
				vCapNhat.set(laptai, row);
			} else {
				Vector row = new Vector();
				row.add(socket);
				row.add(mabh);
				row.add(tenbh);
				row.add(casi);
				row.add(nhacsi);
				row.add(theloai);
				row.add(loi);
				row.add(namst);
				vCapNhat.add(row);
			}
		}

	}
// đưa thông tin vào chuỗi Gui , ServerGui sẽ gửi cho ClientNhan  
	private void NapThongTin(int vitri, Socket socket) {
		try {
			Gui = "";
			int size = vCapNhat.size();
			System.out.println("size =  " + size);
			for (int i = 0; i < size; i++) {
				System.out.println(vCapNhat.get(i));
				Vector row = (Vector) vCapNhat.get(i);
				Socket socketYC = (Socket) row.get(0);
				if (socket == socketYC) {
					int sizeCon = row.size();
					for (int j = 1; j < sizeCon; j++) {
						if (j == (sizeCon - 1)) {
							Gui = Gui + row.get(j).toString();
						} else {
							Gui = Gui + row.get(j).toString() + ";"; 
						}
					}
				}
			}
			System.out.println("Gui = " + Gui);
		} catch (Exception e) {
			
		}

	}
}
