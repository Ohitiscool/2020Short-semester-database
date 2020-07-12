package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.SystemUser;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Addsystemuser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_pwd;
	private SystemUser systemUser;
	private boolean retvalue=false;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Addsystemuser(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 402, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("账号");
			lblNewLabel.setBounds(64, 44, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("名称");
			lblNewLabel_1.setBounds(64, 102, 54, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("密码");
			lblNewLabel_2.setBounds(64, 156, 54, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(checkvaild()) {
					retvalue=true;
					add();
					this.setVisible(false);
				}
			});
			okButton.setBounds(115, 195, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(221, 195, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			textField_id = new JTextField();
			textField_id.setBounds(128, 41, 135, 21);
			contentPanel.add(textField_id);
			textField_id.setColumns(10);
		}
		{
			textField_name = new JTextField();
			textField_name.setBounds(128, 99, 135, 21);
			contentPanel.add(textField_name);
			textField_name.setColumns(10);
		}
		{
			textField_pwd = new JTextField();
			textField_pwd.setBounds(128, 153, 135, 21);
			contentPanel.add(textField_pwd);
			textField_pwd.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean getvalue() {
		systemUser=new SystemUser();
		String id=textField_id.getText();
		String name=textField_name.getText();
		String pwd=textField_pwd.getText();
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "请输入id","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(name.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "请输入名称","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(pwd.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "请输入密码","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		systemUser.setSystemUser_id(id);
		systemUser.setSystemUser_name(name);
		systemUser.setSystemUser_pwd(pwd);
		return true;
	}
	public boolean checkvaild() {
		return getvalue();
	}
	public boolean exec() {
		return retvalue;
	}
	public void add() {
		try {
			LoginStart.userManager.regsystemuser(systemUser.getSystemUser_id(), systemUser.getSystemUser_name(), systemUser.getSystemUser_pwd());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
