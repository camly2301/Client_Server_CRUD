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
	private Vector vCapNhat = new Vector(); // vector b??c t??ch d??? li???u d??ng ????? ?????y l??n tf
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
		// ph???n giao di???n v?? b???t s??? ki???n c??c n??t
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1218, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("M?? b??i h??t:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(734, 65, 101, 29);
		contentPane.add(lblNewLabel);

		JLabel lblTnBiHt = new JLabel("T??n b??i h??t:");
		lblTnBiHt.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnBiHt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnBiHt.setBounds(734, 104, 101, 29);
		contentPane.add(lblTnBiHt);

		JLabel lblCaS = new JLabel("Ca s??:");
		lblCaS.setVerticalAlignment(SwingConstants.TOP);
		lblCaS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCaS.setBounds(734, 156, 101, 25);
		contentPane.add(lblCaS);

		JLabel lblNhcS = new JLabel("Nh???c s??:");
		lblNhcS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhcS.setBounds(734, 191, 101, 29);
		contentPane.add(lblNhcS);

		lblMuBi = new JLabel("Th??? lo???i :");
		lblMuBi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMuBi.setBounds(729, 269, 101, 29);
		contentPane.add(lblMuBi);

		lblNmSngTc = new JLabel("M??? ?????u b??i h??t:");
		lblNmSngTc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmSngTc.setBounds(729, 230, 101, 29);
		contentPane.add(lblNmSngTc);

		// n??t th??m
		JButton btnthem = new JButton("TH??M");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Them();
			}

		});
		btnthem.setBackground(new Color(204, 204, 204));
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBounds(1078, 69, 116, 29);
		contentPane.add(btnthem);

		// n??t s???a
		JButton btnsua = new JButton("S???A");
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sua(tfmbh.getText());
			}

		});
		btnsua.setBackground(new Color(204, 204, 204));
		btnsua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnsua.setBounds(1078, 118, 116, 30);
		contentPane.add(btnsua);

		// n??t x??a
		JButton btnxoa = new JButton("X??A");
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// click chu???t ????? ch???n h??ng mu???n x??a
					selectedRow = table.getSelectedRow();
					// l???y n???i dung h??ng ???? ch???n
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

		// n??t reset l??m m???i 
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

		lblNewLabel_1 = new JLabel("C???ng:");
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

		// n??t b???t server 
		btnBat = new JButton("B???T");
		btnBat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ketnoi == 0) {
					Thread th1 = new Thread() {
						public void run() {
							try {
								tftrangthai.setText("B???t");
								ketnoi = 1;
								// chuy???n tr???ng th??i th??nh B???t r???i g???i h??m BatServer ????? kh???i ?????ng
								BatServer(Integer.parseInt(tfport.getText()), 0); 
						//		System.out.println("k???t n???i th??nh c??ng");
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

		// n??t t???t 
		btnTat = new JButton("T???T");
		btnTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ketnoi == 1) {
					try {
						tftrangthai.setText("T???t");
						server.close();
						//t???t server , socket ??ang k???t n???i c??ng s??? b??? ng???t
						BatServer(Integer.parseInt(tfport.getText().toString()), 1);
						ketnoi = 0;
						int size = vListSocket.size();
						for (int i = 0; i < size; i++) {
							Vector row = (Vector) vListSocket.get(i); // l???y ph???n t??? th??? i vListSocket b??? v??o row
							Socket socket = (Socket) row.get(0);
							ServerGui svg = new ServerGui(socket, "exit"); // g???i th??ng b??o cho socket l?? server ???? t???t 
							svg.start();
						}
						vListSocket.clear(); // x??a c??c socket c?? ???? tr??n b???ng 
						modelsocket.fireTableDataChanged(); // reload l???i b???ng
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

		// n??t ng???t k???t n???i 
		btnNgatkn = new JButton("NG???T K???T N???I");
		btnNgatkn.setBackground(new Color(204, 204, 204));
		btnNgatkn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedListSocket = table_1.getSelectedRow(); // ch???n socket mu???n ng???t
					Vector row = (Vector) vListSocket.get(selectedListSocket); // l???y th??ng tin socket ???? d??ng ph????ng th???c get() c???a vector
					Socket socket = (Socket) row.get(0);
					ServerGui svg = new ServerGui(socket, "exit"); // g???i th??ng b??o cho socket ???? l?? server ???? ng???t k???t n???i 
					svg.start();
					vListSocket.remove(selectedListSocket); // x??a socket ???? ra kh???i modelsocket
					JOptionPane.showMessageDialog(contentPane, "???? ng???t k???t n???i");
					modelsocket.fireTableDataChanged();
					socket.close(); // ng???t k???t n???i socket
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNgatkn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNgatkn.setBounds(423, 322, 158, 25);
		contentPane.add(btnNgatkn);

		// n??t g???i th??ng tin 
		btngui = new JButton("G???I TH??NG TIN");
		btngui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// thread ????? g???i th??ng tin 
				Thread th1 = new Thread() {
					@Override
					public void run() {
						int selectedListSocket = table_1.getSelectedRow();
						Vector row = (Vector) vListSocket.get(selectedListSocket);
						Socket socket = (Socket) row.get(0); // l???y socket (??? col 0 l?? socket)
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

		// n??t xem y??u c???u s???a
		btnxemycs = new JButton("XEM Y??U C???U S???A");
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
					Vector row = (Vector) vCapNhat.get(i); // l???y gi?? tr??? ??? ph???n t??? i c???a vCapNhat 
					Socket socketUp = (Socket) row.get(0);
					System.out.println(socketUp);
					if (socket == socketUp) {
						// ????a l??n TextField
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

		lbltrangthai = new JLabel("Tr???ng th??i:");
		lbltrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltrangthai.setBounds(224, 65, 79, 29);
		contentPane.add(lbltrangthai);

		tftrangthai = new JTextField();
		tftrangthai.setHorizontalAlignment(SwingConstants.CENTER);
		tftrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tftrangthai.setEditable(false);
		tftrangthai.setText("T???t");
		tftrangthai.setBounds(297, 64, 42, 31);
		contentPane.add(tftrangthai);
		tftrangthai.setColumns(10);
		
		// n??t ?????ng ?? s???a 
		btndongy = new JButton("?????NG ?? S???A");
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
					if (lenh.equalsIgnoreCase("C???p nh???t")) {
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
						int thanhcong = prst.executeUpdate(); // th???c hi???n l???nh sql
						if (thanhcong > 0) { 
							JOptionPane.showMessageDialog(contentPane, "???? s???a");
							CapNhatTrangThai(socket, "???? c???p nh???t");
							prst = conn.prepareStatement("select * from baihat where MABH = ?");
							prst.setString(1, tfmbh.getText().toString());
							ResultSet rst = prst.executeQuery();
							ResultSetMetaData rstmt = rst.getMetaData();
							int col = rstmt.getColumnCount(); // l???y s??? c???t c???a b???ng 
							String nd = ""; 
							while (rst.next()) { // ????a d??? li???u t???ng c???t v??o chu???i nd 
								for (int i = 1; i <= col; i++) {
									if (i == col) {
										nd = nd + rst.getString(i);
									} else {
										nd = nd + rst.getString(i) + ';';
									}
								}
							}
							ServerGui svg = new ServerGui(socket, "???? s???a"); // g???i cho client 
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

		lblNewLabel_3 = new JLabel("B??I H??T KARAOKE");
		lblNewLabel_3.setForeground(new Color(255, 102, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel_3.setBounds(437, 10, 287, 47);
		contentPane.add(lblNewLabel_3);

		lblNmSngTc_1 = new JLabel("N??m s??ng t??c:");
		lblNmSngTc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmSngTc_1.setBounds(734, 308, 101, 29);
		contentPane.add(lblNmSngTc_1); 

		tfnamst = new JTextField();
		tfnamst.setColumns(10);
		tfnamst.setBounds(845, 308, 205, 25);
		contentPane.add(tfnamst);

		// n??t t??m ki???m
		JButton btnTim = new JButton("T??M KI???M");
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
// thread ????? load l???i b???ng c???p nh???t c??c socket modelsocket
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
					"SELECT MABH AS'M?? b??i h??t', TENBAIHAT AS'T??n b??i h??t', CASI AS'Ca s??', NHACSI AS'Nh???c s??', MODAUBH AS'M??? ?????u b??i h??t', THELOAI AS'Th??? lo???i', NAMST AS 'N??m s??ng t??c'  \r\n"
							+ " FROM info_song.baihat;");
			// t???o resultsetmetadata ????? l???y s??? c???t c???a resultset
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
			JOptionPane.showMessageDialog(btnNgatkn, "S???a th??nh c??ng");
		} else {
			JOptionPane.showMessageDialog(btnNgatkn, "S???a kh??ng th??nh c??ng");
		}

	}

	private void Them() {
		try {
			PreparedStatement pstm = conn.prepareStatement(
					"insert into baihat(MABH,TENBAIHAT ,CASI,NHACSI ,MODAUBH, THELOAI, NAMST) values (?,?,?,?,?,?,?)");
			conn.setAutoCommit(false);// ch???n g???i l???nh ??i
			pstm.setString(1, tfmbh.getText());
			pstm.setString(2, tftbh.getText());
			pstm.setString(3, tfcasi.getText());
			pstm.setString(4, tfnhacsi.getText());
			pstm.setString(5, tfloi.getText());
			pstm.setString(6, tftheloai.getText());
			pstm.setString(7, tfnamst.getText());
			duochaykhong = pstm.executeUpdate();
			conn.commit();// g???i l???nh ??i ,?????n ????y l???nh m???i ???????c th???c hi???n
		} catch (Exception e2) {
			// TODO: handle exception
		}
		if (duochaykhong > 0) {
			JOptionPane.showMessageDialog(btnNgatkn, "Th??m th??nh c??ng");
		}

		else {
			JOptionPane.showMessageDialog(btnNgatkn, "Tr??ng ID");
		}

	}

	private void BatServer(int port, int i) {
		try {
			// i == 0 server ??ang b???t -> kh??ng ch???y, i == 1 server ch??a b???t -> server s??? b???t
			if (i == 1) {
				server.close();
			} else {
				server = new ServerSocket(Integer.parseInt(tfport.getText())); // l???y ?????a ch??? c???ng : 8788
				while (true) {
					if (i == 1) {
						break;
					}
					
					Socket socket = server.accept();
					System.out.println("dong y");
					ServerNhan svn = new ServerNhan(socket);
					Vector row = new Vector();
					// add c??c socket k???t n???i v??o 
					row.add(socket);
					row.add(1, "");
					row.add(2, "??ang k???t n???i...");
					row.add(3, "");
					row.add(4, "");

					Thread th1 = new Thread() {
						@Override
						public void run() {
							while (true) {
								if (svn.getSms() != null) { // sms t??? ClientGui 
									System.out.println("sms = " + svn.getSms());
									if (svn.getSms().equals("exit")) {
										CapNhatTrangThai(socket, "???? tho??t");
										svn.setSms(null);
									} else {
										if (svn.getSms().contains("sqltruyvan")) {
											ndMBH = svn.getNdMBH();
											ndTBH = svn.getndTen();
											row.set(1, "Truy v???n");
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
											CapNhatTrangThai(socket, "C???p nh???t");

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
			vTitlelistsocket.add("Socket ??ang k???t n???i");
			vTitlelistsocket.add("Y??u c???u");
			vTitlelistsocket.add("Tr???ng th??i");
			vTitlelistsocket.add("M?? b??i h??t");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void CapNhatTrangThai(Socket socket, String lenh) {
		int size = vListSocket.size();
		if (lenh.equalsIgnoreCase("???? tho??t")) {
			for (int i = 0; i < size; i++) {
				Vector row = (Vector) vListSocket.get(i);
				Socket socketList = (Socket) row.get(0);
				if (socket == socketList) {
					vListSocket.remove(i);
					JOptionPane.showMessageDialog(contentPane, "Socket =" + socket + "???? Tho??t", "Th??ng b??o",
							JOptionPane.ERROR_MESSAGE);
				}
				modelsocket.fireTableDataChanged();
			}
		} 
		else if (lenh.equalsIgnoreCase("C???p nh???t")) {
			for (int i = 0; i < size; i++) {
				Vector row = (Vector) vListSocket.get(i);
				Socket socketList = (Socket) row.get(0);
				if (socket == socketList) {
					row.set(1, lenh);
					row.set(2,"T???n t???i");
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
			// n???u ch??a c?? socket n??o h???t 
			if (size == 0) {
				Vector row = new Vector();
				row.add(socket);
				while (rst.next()) { // ?????n nh???ng n???i dung c???a c???t != null
					kiemtraketqua = 1;
					for (int i = 1; i <= col; i++) {
						row.add(rst.getString(i));
					}
					vCapNhat.add(row);
				}
				//n???u ???? c?? socket v?? s??? ki???m tra xem socket n??y c?? t???n t???i ch??a
			} else {
				for (int i = 0; i < size; i++) {
					Vector row = (Vector) vCapNhat.get(i);
					Socket socketCheck = (Socket) row.get(0);
					if (socketCheck == socket) {
						trunglap = 1;
						vitritrung = i;
					}
				}
				// n???u kh??ng c?? tr??ng l???p th?? th??m m???i 
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
					//n???u ???? c?? socket th?? ch??? c???n thay th??? n???i dung l?? ???????c
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
				CapNhatTrangThai(socket, "T???n t???i");
				ServerGui qtg = new ServerGui(socket, "T???n t???i");
				qtg.start();
			} else {
				CapNhatTrangThai(socket, "Kh??ng t???n t???i");
				ServerGui qtg = new ServerGui(socket, "Kh??ng t???n t???i");
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
// ????a th??ng tin v??o vCapNhat 
	private void CapnhatvaovCapNhat(Socket socket, String mabh, String tenbh, String casi, String nhacsi,
			String theloai, String loi, String namst) {
		int size = vCapNhat.size();
		if (size == 0) { // add d??? li???u v??o vCapNhat 
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
		} else { // c?? l???p th?? xem th???ng n??o l???p 
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
// ????a th??ng tin v??o chu???i Gui , ServerGui s??? g???i cho ClientNhan  
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
