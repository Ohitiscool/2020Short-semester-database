package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modifysyspwd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private String pwd1,pwd2;
	private boolean retval=false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Modifysyspwd(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 372, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("原密码");
			lblNewLabel.setBounds(67, 47, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("新密码");
			lblNewLabel_1.setBounds(67, 124, 54, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField = new JTextField();
			textField.setBounds(131, 44, 134, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(131, 121, 134, 21);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(checkvaild()&&modi()) {
					retval=true;
					this.setVisible(false);
				}
				
			});
			okButton.setBounds(107, 195, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(216, 195, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean getvalue() {
		 pwd1=textField.getText();
		if(pwd1.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "请输入原","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		 pwd2=textField_1.getText();
		if(pwd2.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "请输入新","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkvaild() {
		return getvalue();
	}
	public boolean  modi() {
		try {
			LoginStart.userManager.modifysystemuser(pwd1, pwd2);
			return true;
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,  e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	public boolean exec() {
		return retval;
	}

}
