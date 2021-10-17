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
	 * 西南石油大学-敬亚霖
	 * 201931061165
	 */
	
	
	//用户界面
	
	private static final long serialVersionUID = 1L;
	JTable table;
	JLabel label1, label2, label3, label4, label5, label9, label10;
	Object a[][];
	Object name[] = { "联系人编号", "联系人姓名", "联系人电话", "联系人住址"};
	JButton 添加_button;
	JButton 修改_button;
	JButton 删除_button;
	JButton 查询_button;
	JButton 退出_button;
	Box box1, box2;
	JTextField 联系人编号_field;
	JTextField 联系人姓名_field;
	JTextField 联系人电话_field;
	JTextField 联系人地址_field;
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
		setTitle("联系人管理界面-西南石油大学");
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

		label1 = new JLabel("联系人信息");
		label2 = new JLabel("信息操作");
		label3 = new JLabel("联系人姓名");
		label4 = new JLabel("联系人电话");
		label5 = new JLabel("联系人住址");
		label9 = new JLabel("联系人编号");
		label10 = new JLabel("(删除或修改时填写)");
		退出_button = new JButton("退出");
		退出_button.addActionListener(this);

		联系人姓名_field = new JTextField(10);
		联系人电话_field = new JTextField(10);
		联系人地址_field = new JTextField(10);
		联系人编号_field = new JTextField(10);

		a = new Object[100][4];
		table = new JTable(a, name);// 组件的创建
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		box1 = Box.createVerticalBox();
		
		box1.add(label9);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label10);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(联系人编号_field);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label3);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(联系人姓名_field);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label4);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(联系人电话_field);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(label5);
		
		box1.add(Box.createVerticalStrut(6));
		box1.add(联系人地址_field);
		
	    添加_button = new JButton("添加");
	    添加_button.addActionListener(this);
		box1.add(添加_button);
		box1.add(Box.createVerticalStrut(28));
		
		修改_button = new JButton("修改");
		修改_button.addActionListener(this);
		box1.add(修改_button);
		box1.add(Box.createVerticalStrut(28));

		删除_button = new JButton("删除");
		删除_button.addActionListener(this);
		box1.add(删除_button);
		box1.add(Box.createVerticalStrut(28));
		
		查询_button = new JButton("查询");
		查询_button.addActionListener(this);
		box1.add(查询_button);
		box1.add(Box.createVerticalStrut(28));
		
		box1.add(退出_button);
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
	
	
	//业务逻辑
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				Object source = e.getSource();
				

				//查询
				if (source == 查询_button) {
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
				
				
			
				// 添加
				else if (source == 添加_button) {
					int key;
					int point = 0;
					if (联系人地址_field.getText().equals("")
							|| 联系人电话_field.getText().equals("") || 联系人姓名_field.getText().equals("")
							) {
						JOptionPane.showMessageDialog(null, "提示：添加时选项不能为空，请重新填写！");
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
							s = "insert into `contacts` values('" + point + "','" + 联系人姓名_field.getText() + "','"
									+ 联系人电话_field.getText() + "','" + 联系人地址_field.getText() + "')";
							sql.executeUpdate(s);
							JOptionPane.showMessageDialog(null, "添加成功，请点击查询查看");
							联系人姓名_field.setText("");
							联系人电话_field.setText("");
							联系人地址_field.setText("");
							联系人编号_field.setText("");
						} catch (SQLException e2) {
							e2.printStackTrace();
							System.out.println("数据库连接失败3");
						}
						try {
							sql.close();
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				
				
				
				// 删除
				else if (source == 删除_button) {
					int num = 0, key1 = 0;
					if (联系人编号_field.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "提示：删除操作时联系人编号选项不能为空，请重新填写！");
					} else {
						int num1 = Integer.parseInt(联系人编号_field.getText());
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
								JOptionPane.showMessageDialog(null, "提示：系统不存在此联系人编号，请重新填写！");
								联系人编号_field.setText("");
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
								JOptionPane.showMessageDialog(null, "删除成功！");
								联系人编号_field.setText("");
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
				
				
				
				// 修改
				else if (source == 修改_button) {
					int key2 = 0;
					int num2=0, num3 = 0;
					if (联系人编号_field.getText().equals("") || 联系人电话_field.getText().equals("") || 联系人地址_field.getText().equals("")
							|| 联系人姓名_field.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "提示：修改时所有选项不能为空，请重新填写！");
					} else {
						try {
							num2 = Integer.parseInt(联系人编号_field.getText());
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
								JOptionPane.showMessageDialog(null, "提示：系统不存在此联系人信息，请重新填写！");
								联系人编号_field.setText("");
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
										+ 联系人姓名_field.getText() + "',`phone`='" + 联系人电话_field.getText() + "',`address`='"
										+ 联系人地址_field.getText() + "' where id='" + num3 + "'";
								sql.executeUpdate(sql2);
								JOptionPane.showMessageDialog(null, "修改成功，请刷新后查看");
								联系人姓名_field.setText("");
								联系人编号_field.setText("");
								联系人地址_field.setText("");
								联系人电话_field.setText("");
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
				
				
				// 退出
				else if (source == 退出_button) {
					this.dispose();
				}

				
				
				
				
		
	}
}