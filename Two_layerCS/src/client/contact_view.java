package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import server.GetDBConnection;


import java.sql.*;



public class contact_view extends JFrame implements ActionListener {
	

	/**
	 * ����ʯ�ʹ�ѧ-������
	 * 201931061165
	 */
	
	
	//�û�����
	
	private static final long serialVersionUID = 1L;
	JTable table;
	JLabel label1, label2, label3, label4, label5, label9, label10;
	Object a[][];
	Object name[] = { "��ϵ�˱��", "��ϵ������", "��ϵ�˵绰", "��ϵ��סַ"};
	JButton ���_button;
	JButton �޸�_button;
	JButton ɾ��_button;
	JButton ��ѯ_button;
	JButton �˳�_button;
	Box box1, box2;
	JTextField ��ϵ�˱��_field;
	JTextField ��ϵ������_field;
	JTextField ��ϵ�˵绰_field;
	JTextField ��ϵ�˵�ַ_field;
	JPanel jPanel1, jPanel2;
	Connection con = null;
	Statement sql = null;
	ResultSet rs = null;
	String s;

	public contact_view() {
		init();
		repaint();
		setVisible(true);
		setResizable(true);
		setBounds(500, 200, 1000, 680);
		setTitle("��ϵ�˹������-����ʯ�ʹ�ѧ");
	}

	void init() {
		JPanel photo = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("D:\\img\\pic1.jpg");
				g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), imageIcon.getImageObserver());
			}
		};

		label1 = new JLabel("��ϵ����Ϣ");
		label2 = new JLabel("��Ϣ����");
		label3 = new JLabel("��ϵ������");
		label4 = new JLabel("��ϵ�˵绰");
		label5 = new JLabel("��ϵ��סַ");
		label9 = new JLabel("��ϵ�˱��");
		label10 = new JLabel("(ɾ�����޸�ʱ��д)");
		�˳�_button = new JButton("�˳�");
		�˳�_button.addActionListener(this);

		��ϵ������_field = new JTextField(10);
		��ϵ�˵绰_field = new JTextField(10);
		��ϵ�˵�ַ_field = new JTextField(10);
		��ϵ�˱��_field = new JTextField(10);

		a = new Object[100][4];
		table = new JTable(a, name);// ����Ĵ���
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		box1 = Box.createVerticalBox();
		
		box1.add(label9);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label10);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(��ϵ�˱��_field);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label3);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(��ϵ������_field);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label4);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(��ϵ�˵绰_field);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label5);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(��ϵ�˵�ַ_field);
		
	    ���_button = new JButton("���");
	    ���_button.addActionListener(this);
		box1.add(���_button);
		box1.add(Box.createVerticalStrut(28));
		
		�޸�_button = new JButton("�޸�");
		�޸�_button.addActionListener(this);
		box1.add(�޸�_button);
		box1.add(Box.createVerticalStrut(28));

		ɾ��_button = new JButton("ɾ��");
		ɾ��_button.addActionListener(this);
		box1.add(ɾ��_button);
		box1.add(Box.createVerticalStrut(28));
		
		��ѯ_button = new JButton("��ѯ");
		��ѯ_button.addActionListener(this);
		box1.add(��ѯ_button);
		box1.add(Box.createVerticalStrut(28));
		
		box1.add(�˳�_button);
		box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(28));
		
		box2.add(box1);
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel2.setLayout(new BorderLayout());
		jPanel2.add(label1, BorderLayout.NORTH);
		jPanel2.setPreferredSize(new Dimension(600, 641));
		jPanel2.add(scrollPane, BorderLayout.CENTER);
		jPanel1.setLayout(new BorderLayout());
		jPanel1.add(label2, BorderLayout.NORTH);
		jPanel1.add(box2, BorderLayout.CENTER);
		getContentPane().setLayout(new BorderLayout());
		photo.add(jPanel2, BorderLayout.WEST);
		photo.add(jPanel1, BorderLayout.EAST);
		getContentPane().add(photo);
	}
	
	
	//ҵ���߼�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				Object source = e.getSource();
				

				//��ѯ
				if (source == ��ѯ_button) {
					int i = 0;
					while (i < 100) {
						a[i][0] = " ";
						a[i][1] = " ";
						a[i][2] = " ";
						a[i][3] = " ";
						i++;
					}
					i = 0;
					con = GetDBConnection.connectDB("con", "root", "");
					try {
						sql = con.createStatement();
						s = "select * from `contacts`";
						rs = sql.executeQuery(s);
						while (rs.next()) {
							int contactNumber = rs.getInt(1);
							String contactName = rs.getString(2);
							String contactPhone = rs.getString(3);
							String contactAddress = rs.getString(4);
						

							a[i][0] = contactNumber;
							a[i][1] = contactName;
							a[i][2] = contactPhone;
							a[i][3] = contactAddress;
							i++;
						}
						repaint();
						try {
							sql.close();
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			
				// ���
				else if (source == ���_button) {
					int key;
					int point = 0;
					if (��ϵ�˵�ַ_field.getText().equals("")
							|| ��ϵ�˵绰_field.getText().equals("") || ��ϵ������_field.getText().equals("")
							) {
						JOptionPane.showMessageDialog(null, "��ʾ�����ʱѡ���Ϊ�գ���������д��");
					} else {
						try {
							con = GetDBConnection.connectDB("con", "root", "");
							sql = con.createStatement();
							s = "select MAX(`id`) from `contacts`";
							rs = sql.executeQuery(s);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						try {
							if (rs.next()) {
								key = rs.getInt(1);
								point = key + 1;
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							sql.close();
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							con = GetDBConnection.connectDB("con", "root", "");
							sql = con.createStatement();
							s = "insert into `contacts` values('" + point + "','" + ��ϵ������_field.getText() + "','"
									+ ��ϵ�˵绰_field.getText() + "','" + ��ϵ�˵�ַ_field.getText() + "')";
							sql.executeUpdate(s);
							JOptionPane.showMessageDialog(null, "��ӳɹ���������ѯ�鿴");
							��ϵ������_field.setText("");
							��ϵ�˵绰_field.setText("");
							��ϵ�˵�ַ_field.setText("");
							��ϵ�˱��_field.setText("");
						} catch (SQLException e2) {
							e2.printStackTrace();
							System.out.println("���ݿ�����ʧ��3");
						}
						try {
							sql.close();
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				
				
				
				// ɾ��
				else if (source == ɾ��_button) {
					int num = 0, key1 = 0;
					if (��ϵ�˱��_field.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "��ʾ��ɾ������ʱ��ϵ�˱��ѡ���Ϊ�գ���������д��");
					} else {
						int num1 = Integer.parseInt(��ϵ�˱��_field.getText());
						try {
							con = GetDBConnection.connectDB("con", "root", "");
							sql = con.createStatement();
							s = "select * from `contacts` where id='" + num1 + "'";
							rs = sql.executeQuery(s);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						try {
							if (rs.next()) {
								num = rs.getInt(1);
								key1 = 1;
							} else {
								JOptionPane.showMessageDialog(null, "��ʾ��ϵͳ�����ڴ���ϵ�˱�ţ���������д��");
								��ϵ�˱��_field.setText("");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							sql.close();
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if (key1 == 1) {
							key1 = 0;
							try {
								con = GetDBConnection.connectDB("con", "root", "");
								sql = con.createStatement();
								s = "delete from `contacts` where id='" + num + "'";
								@SuppressWarnings("unused")
								int a = sql.executeUpdate(s);
								JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
								��ϵ�˱��_field.setText("");
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
							try {
								sql.close();
								con.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				
				
				
				// �޸�
				else if (source == �޸�_button) {
					int key2 = 0;
					int num2=0, num3 = 0;
					if (��ϵ�˱��_field.getText().equals("") || ��ϵ�˵绰_field.getText().equals("") || ��ϵ�˵�ַ_field.getText().equals("")
							|| ��ϵ������_field.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "��ʾ���޸�ʱ����ѡ���Ϊ�գ���������д��");
					} else {
						try {
							num2 = Integer.parseInt(��ϵ�˱��_field.getText());
							con = GetDBConnection.connectDB("con", "root", "");
							sql = con.createStatement();
							s = "select * from `contacts` where id='" + num2 + "'";
							rs = sql.executeQuery(s);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						try {
							if (rs.next()) {
								num3 = rs.getInt(1);
								key2 = 1;
							} else {
								JOptionPane.showMessageDialog(null, "��ʾ��ϵͳ�����ڴ���ϵ����Ϣ����������д��");
								��ϵ�˱��_field.setText("");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							sql.close();
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if (key2 == 1) {
							key2 = 0;
							try {
								con = GetDBConnection.connectDB("con", "root", "");
								sql = con.createStatement();
								String sql2 = "update `contacts` set `id`='" + num2 + "',`name`='"
										+ ��ϵ������_field.getText() + "',`phone`='" + ��ϵ�˵绰_field.getText() + "',`address`='"
										+ ��ϵ�˵�ַ_field.getText() + "' where id='" + num3 + "'";
								sql.executeUpdate(sql2);
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�����ˢ�º�鿴");
								��ϵ������_field.setText("");
								��ϵ�˱��_field.setText("");
								��ϵ�˵�ַ_field.setText("");
								��ϵ�˵绰_field.setText("");
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
							try {
								sql.close();
								con.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}

				}
				
				
				// �˳�
				else if (source == �˳�_button) {
					this.dispose();
				}

				
				
				
				
		
	}
}