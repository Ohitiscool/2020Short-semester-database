package ui;

import system.ui.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.UserManager;
import model.Product;
import model.SystemUser;
import model.User;
import util.BaseException;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import start.*;
public class Flogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flogin frame = new Flogin();
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
	public Flogin() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("登入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserId = new JLabel("账号：");
		lblUserId.setFont(new Font("宋体", Font.PLAIN, 13));
		lblUserId.setBounds(64, 64, 65, 15);
		contentPane.add(lblUserId);
		
		textField = new JTextField();
		textField.setBounds(135, 61, 172, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("密码：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel.setBounds(64, 101, 65, 15);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 98, 172, 21);
		contentPane.add(passwordField);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(180, 129, 72, 21);
		comboBox.addItem("管理员");
		comboBox.addItem("会员");
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("登入");
		btnNewButton.setBounds(247, 195, 93, 23);
		btnNewButton.addActionListener((e)->{
			String item=(String)comboBox.getSelectedItem();
			String userid=this.textField.getText();
			String pwd=new String(this.passwordField.getPassword());
			if(item.equals("管理员")) {
				try {
					SystemUser.currenSystemUser=new UserManager().systemUserlogin(userid, pwd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"错误", JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				    return;
				}
				this.setVisible(false);
				SystemMain systemMain=new SystemMain();
				systemMain.setVisible(true);
				
			}
			else if(item.equals("会员")) {
				try {
					User.currentLoginUser=LoginStart.userManager.login(userid, pwd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"错误", JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return ;//有错误要返回到原来的窗口的
				}
				this.setVisible(false);
				FMain fmain=new FMain();
				fmain.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.addActionListener((e)->{
			Freg freg=new Freg();
			freg.setFlogin(this);
			freg.setVisible(true);  
			this.setEnabled(false);//阻塞传递  ，类似消息对话框
		});
		btnNewButton_1.setBounds(84, 195, 93, 23);
		contentPane.add(btnNewButton_1);
	}
	public Flogin getFlogin(Flogin flogin) {
		return this;
	}
	
}
