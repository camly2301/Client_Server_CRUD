package CuoiKy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ServerGD extends JFrame {

	private JPanel contentPane;
	private JTextField tfport;
	private JTable table;
	private DefaultTableModel model;
	private Vector vData = new Vector();
	private Vector vTitle = new Vector();
	private JTextField tfgui;
	private JButton btngui;

	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGD frame = new ServerGD();
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
	public ServerGD() {
		NaptenSocket();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfport = new JTextField();
		tfport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfport.setText("8787");
		tfport.setBounds(69, 50, 96, 19);
		contentPane.add(tfport);
		tfport.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 682, 282);
		contentPane.add(scrollPane);

		model = new DefaultTableModel(vData,vTitle);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Kết nối");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BatSocket();
			}

		});
		btnNewButton.setBounds(218, 39, 124, 41);
		contentPane.add(btnNewButton);
		
		tfgui = new JTextField();
		tfgui.setBounds(392, 52, 186, 19);
		contentPane.add(tfgui);
		tfgui.setColumns(10);
		
		btngui = new JButton("Gửi");
		btngui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				Vector row = (Vector) vData.get(i);
				Socket socket = (Socket) row.get(0);
				ServerViet svv = new ServerViet(socket, tfgui.getText());
				svv.start();
			}
		});
		btngui.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btngui.setBounds(589, 49, 85, 21);
		contentPane.add(btngui);
	}

	private void NaptenSocket() {
		// TODO Auto-generated method stub
		vTitle.add("ten socket");
	}

	private void BatSocket() {
		try {
			ServerSocket server = new ServerSocket(Integer.parseInt(tfport.getText()));
			Thread th1 = new Thread() {
				@Override
				public void run() {
					try {
						while(true) {
							Vector row = new Vector();
							Socket socket = server.accept();
							row.add(socket);
							ServerDoc svd = new ServerDoc(socket);
							svd.start();
							vData.add(row);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			};
			th1.start();
			Thread th2 = new Thread() {
				@Override
				public void run() {
					while (true) {
						model.fireTableDataChanged();
						try {
							Thread.sleep(5000);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			};
			th2.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
