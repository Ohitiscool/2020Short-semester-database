package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import control.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.security.auth.login.LoginContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.*;
import util.*;
import javax.swing.JComboBox;


public class Freg extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_name;
	
	private JTextField textField_tel;
	private JTextField textField_email;
	private JTextField textField_city;
	private Flogin flogin;
	private JComboBox<String> sexselectBox=new JComboBox<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Freg frame = new Freg();
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
	public Freg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setBounds(65, 10, 65, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setBounds(65, 46, 65, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(135, 7, 172, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 47, 172, 21);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.setBounds(100, 298, 93, 23);
		btnNewButton.addActionListener((e)->{
			String userid=this.textField.getText();
			String pwd=new String(this.passwordField.getPassword());
			String name=this.textField_name.getText();
			String tel=this.textField_tel.getText();
			String email=this.textField_email.getText();
			String city=this.textField_city.getText();
			try {
				new UserManager().reg(userid, pwd, name, tel, email, city, jud());
				flogin.setEnabled(true);
				this.setVisible(false);
			}catch(BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.setBounds(231, 298, 93, 23);
		btnNewButton_1.addActionListener((e)->{
			flogin.setEnabled(true);
			this.setVisible(false);
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("名字：");
		lblNewLabel_2.setBounds(65, 89, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("性别：");
		lblNewLabel_3.setBounds(65, 130, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("电话：");
		lblNewLabel_4.setBounds(65, 170, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("邮箱：");
		lblNewLabel_5.setBounds(65, 210, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("城市：");
		lblNewLabel_6.setBounds(65, 250, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_name = new JTextField();
		textField_name.setBounds(135, 87, 172, 21);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_tel = new JTextField();
		textField_tel.setBounds(135, 167, 172, 21);
		contentPane.add(textField_tel);
		textField_tel.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBounds(135, 207, 172, 21);
		contentPane.add(textField_email);
		textField_email.setColumns(10);
		
		textField_city = new JTextField();
		textField_city.setBounds(135, 247, 172, 21);
		contentPane.add(textField_city);
		textField_city.setColumns(10);
		
		sexselectBox.setBounds(183, 127, 65, 21);
		sexselectBox.setSize(54, 21);
		sexselectBox.setLocation(178, 127);
		sexselectBox.addItem("男");
		sexselectBox.addItem("女");
		contentPane.add(sexselectBox);
		
	}
	public void setFlogin(Flogin flogin) {
		this.flogin=flogin;
	}
	private String jud() {
		String sex=(String)sexselectBox.getSelectedItem();
		System.out.println(sex);
		if(sex.equals("男")) return "male";
		else return "female";
	}
}
