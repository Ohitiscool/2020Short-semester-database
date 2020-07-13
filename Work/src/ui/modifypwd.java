package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Executable;

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

public class modifypwd extends JDialog {

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
	public modifypwd(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 403, 285);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("原密码");
		lblNewLabel.setBounds(72, 38, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("新密码");
		lblNewLabel_1.setBounds(72, 101, 54, 15);
		contentPanel.add(lblNewLabel_1);
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(getvalue()) {
					modify();
					retval=true;
					this.setVisible(false);
				}
			});
			okButton.setBounds(113, 166, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(233, 166, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		textField = new JTextField();
		textField.setBounds(136, 35, 131, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 98, 131, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean getvalue() {
		pwd1=textField.getText();
		pwd2=textField_1.getText();
		if(pwd1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "原密码字段不得为空", "错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(pwd2.isEmpty()) {
			JOptionPane.showMessageDialog(null, "新密码字段不得为空", "错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	private void modify() {
		try {
			LoginStart.userManager.modifypwduser(pwd1, pwd2);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean exec() {
		return retval;
	}
}
