package CuoiKy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ClientMain extends JFrame {

	private JPanel contentPane;
	private JTextField tfmbh,tftbh,tfcasi, tfnhacsi, tftheloai, tfloi,tfnamst,tfcong,tftrangthai,tfma, tften ;
	private int ketnoi = 0;
	private Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMain frame = new ClientMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("USER");
		lblNewLabel.setForeground(new Color(255, 102, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(414, 10, 99, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tra cứu thông tin bài hát karaoke");
		lblNewLabel_1.setForeground(new Color(255, 51, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(300, 48, 335, 26);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(478, 134, 425, 331);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Mã bài hát :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(27, 24, 90, 29);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Tên bài hát :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(27, 65, 90, 29);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Ca sĩ :");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(27, 104, 90, 29);
		panel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("Nhạc sĩ :");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(27, 143, 90, 29);
		panel.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_3 = new JLabel("Thể loại :");
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_3.setBounds(27, 182, 115, 29);
		panel.add(lblNewLabel_2_1_3);

		JLabel lblNewLabel_2_1_4 = new JLabel("Mở đầu bài hát :");
		lblNewLabel_2_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_4.setBounds(27, 221, 115, 29);
		panel.add(lblNewLabel_2_1_4);

		tfmbh = new JTextField();
		tfmbh.setBounds(156, 24, 219, 26);
		panel.add(tfmbh);
		tfmbh.setColumns(10);

		JLabel lblNewLabel_2_1_4_1 = new JLabel("Năm sáng tác :");
		lblNewLabel_2_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_4_1.setBounds(27, 260, 115, 29);
		panel.add(lblNewLabel_2_1_4_1);

		tftbh = new JTextField();
		tftbh.setColumns(10);
		tftbh.setBounds(156, 65, 219, 26);
		panel.add(tftbh);

		tfcasi = new JTextField();
		tfcasi.setColumns(10);
		tfcasi.setBounds(156, 104, 219, 26);
		panel.add(tfcasi);

		tfnhacsi = new JTextField();
		tfnhacsi.setColumns(10);
		tfnhacsi.setBounds(156, 143, 219, 26);
		panel.add(tfnhacsi);

		tftheloai = new JTextField();
		tftheloai.setColumns(10);
		tftheloai.setBounds(156, 182, 219, 26);
		panel.add(tftheloai);

		tfloi = new JTextField();
		tfloi.setColumns(10);
		tfloi.setBounds(156, 221, 219, 26);
		panel.add(tfloi);

		tfnamst = new JTextField();
		tfnamst.setColumns(10);
		tfnamst.setBounds(156, 260, 219, 26);
		panel.add(tfnamst);

		JButton btnycs = new JButton("YÊU CẦU SỬA");
		btnycs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(ketnoi);
					if(ketnoi == 1) {
						
						String mabh = tfmbh.getText().toString();
						String tenbh = tftbh.getText().toString();
						String casi = tfcasi.getText().toString();
						String nhacsi = tfnhacsi.getText().toString();
						String loi = tfloi.getText().toString();
						String theloai = tftheloai.getText().toString();
						String namst = tfnamst.getText().toString();
						String sms = "update"+ ";" + mabh + ";" + tenbh + ";" + casi + ";" + 
						nhacsi + ";" + loi + ";" + theloai + ";" + namst;
					//	System.out.println(sms);
						ClientGui clg = new ClientGui(socket, sms);
						clg.start();
						JOptionPane.showMessageDialog(contentPane, "Đã gửi yêu cầu sửa cho Server, vui lòng chờ...");
					}else {
						JOptionPane.showMessageDialog(contentPane, "Chưa kết nối Server, vui lòng kết nối!");
					
					}
				} catch (Exception e2) {
					
				}
			}
		});
		
		btnycs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnycs.setBounds(550, 493, 145, 35);
		contentPane.add(btnycs);

		JButton btnLammoi = new JButton("LÀM MỚI");
		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfmbh.setText("");
				tftbh.setText("");
				tfcasi.setText("");
				tfnhacsi.setText("");
				tfloi.setText("");
				tftheloai.setText("");
				tfnamst.setText("");
			}
		});
		btnLammoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLammoi.setBounds(724, 493, 120, 35);
		contentPane.add(btnLammoi);

		JButton btnThoat = new JButton("THOÁT");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.exit(0);
				} catch (Exception e2) {
					
				}
			}
		});
		btnThoat.setForeground(Color.RED);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(31, 493, 114, 35);
		contentPane.add(btnThoat);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 66, 166, 58);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblcong = new JLabel("Cổng ");
		lblcong.setHorizontalAlignment(SwingConstants.CENTER);
		lblcong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcong.setBounds(10, 20, 47, 22);
		panel_1.add(lblcong);

		tfcong = new JTextField();
		tfcong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfcong.setText("8788");
		tfcong.setBounds(62, 21, 96, 26);
		panel_1.add(tfcong);
		tfcong.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(20, 134, 415, 331);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Nhập thông tin");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(143, 24, 97, 24);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Mã bài hát ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(30, 79, 122, 31);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Tên bài hát");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6_1.setBounds(30, 142, 108, 31);
		panel_2.add(lblNewLabel_6_1);

		tfma = new JTextField();
		tfma.setColumns(10);
		tfma.setBounds(162, 79, 219, 26);
		panel_2.add(tfma);

		tften = new JTextField();
		tften.setColumns(10);
		tften.setBounds(162, 142, 219, 26);
		panel_2.add(tften);

		JButton btnNewButton_1 = new JButton("XEM THÔNG TIN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ketnoi == 1) {
					LamMoi();
					Thread th2 = new Thread() {
						public void run() {
							String noidung = "sqltruyvan" + ";" + tfma.getText().toString() + ";"
									+ tften.getText().toString();
							ClientGui clg = new ClientGui(socket, noidung);
							clg.start();
							JOptionPane.showMessageDialog(contentPane, "Đã gửi yêu cầu xem thông tin cho server, vui lòng đợi...");
						}
					};
					th2.start();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Chưa kết nối Server, vui lòng kết nối!");
				}
			}

		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(124, 223, 161, 24);
		panel_2.add(btnNewButton_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(186, 66, 145, 58);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lbltrangthai = new JLabel("Trạng thái");
		lbltrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltrangthai.setBounds(10, 20, 68, 23);
		panel_3.add(lbltrangthai);

		tftrangthai = new JTextField();
		tftrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tftrangthai.setEditable(false);
		tftrangthai.setText("TẮT");
		tftrangthai.setBounds(89, 20, 42, 25);
		panel_3.add(tftrangthai);
		tftrangthai.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(620, 77, 270, 47);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JButton btnketnoi = new JButton("Kết nối");
		btnketnoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ketnoi == 0) {
						BatSocket();
						if(socket != null) {
							tftrangthai.setText("BẬT");
							ketnoi = 1;
						}else {
							JOptionPane.showMessageDialog(contentPane, "Server chưa kết nối vui lòng kết nối lại..");
							tftrangthai.setText("TẮT");
						}
					}
				} catch (Exception e2) {
					
				}
			}

		});
		btnketnoi.setBounds(10, 10, 85, 25);
		panel_4.add(btnketnoi);
		btnketnoi.setForeground(new Color(0, 204, 0));
		btnketnoi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnngatkn = new JButton("Ngắt kết nối");
		btnngatkn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (socket == null) {
						tftrangthai.setText("TẮT");
						ketnoi = 0;
					}else {
						ClientGui clg = new ClientGui(socket,"exit");
						clg.start();
						Thread th1 = new Thread() {
							public void run() {
								try {
									Thread.sleep(1000);
									socket.close();
									socket = null;
									tftrangthai.setText("TẮT");
									ketnoi = 0;
								} catch (Exception e2) {
									// TODO: handle exception
								}
							};
						};
						th1.start();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnngatkn.setBounds(105, 10, 123, 25);
		panel_4.add(btnngatkn);
		btnngatkn.setForeground(new Color(255, 0, 0));
		btnngatkn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}

	private void BatSocket() {
		
		try {
			socket = new Socket("localhost", Integer.parseInt(tfcong.getText()));
			ClientNhan cln = new ClientNhan(socket);
			cln.start();
			Thread th1 = new Thread() {
				@Override
				public void run() {
					while(true) {
						if(cln.getNdnhan() != "") {
							if(cln.getNdnhan().equalsIgnoreCase("exit")) {
								System.out.println();
								JOptionPane.showMessageDialog(contentPane, "Server đã ngắt kêt nối vui lòng kết nối lại...");
								tftrangthai.setText("TẮT");
								ketnoi = 0;
								break;
							}else {
								if (cln.getNdnhan().equalsIgnoreCase("Đã sửa")) {
									JOptionPane.showMessageDialog(contentPane,"Sever đã sửa");
								}
								if(cln.getNdnhan().equalsIgnoreCase("Không tồn tại")) {
									JOptionPane.showMessageDialog(contentPane,"Không tồn tại");
									
								}
								// ClientNhan nhận thông tin từ ServerGui rồi đưa lên tf 	
								if(cln.getNdnhan().length() > 20) { 
									tfmbh.setText(cln.getMabh());
									tftbh.setText(cln.getTenbh());
									tfcasi.setText(cln.getCasi());
									tfnhacsi.setText(cln.getNhacsi());
									tfloi.setText(cln.getLoi());
									tftheloai.setText(cln.getTheloai());
									tfnamst.setText(cln.getNamst());
							//		System.out.println("getMabn: " + cln.getMabh() );		
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
			if (socket != null) {
				th1.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void LamMoi() {
		tfmbh.setText("");
		tftbh.setText("");
		tfcasi.setText("");
		tfnhacsi.setText("");
		tfloi.setText("");
		tftheloai.setText("");
		tfnamst.setText("");
	}
}
