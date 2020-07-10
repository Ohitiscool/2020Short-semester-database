package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.*;
import start.LoginStart;
import util.BaseException;
public class Modify_address extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_province;
	private JTextField textField_city;
	private JTextField textField_area;
	private JTextField textField_person;
	private JTextField textField_tel;
	private Address address=null;
	private JButton okButton,cancelButton;
	boolean retvalue=false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Modify_address(JFrame f,String s,boolean model,Address address) {
		super(f,s,model);
		this.address=address;
		setBounds(100, 100, 450, 421);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("省份");
		lblNewLabel.setBounds(52, 41, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("城市");
		lblNewLabel_1.setBounds(52, 91, 54, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("地区");
		lblNewLabel_2.setBounds(52, 141, 54, 15);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("联系人");
		lblNewLabel_3.setBounds(52, 191, 54, 15);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("联系号码");
		lblNewLabel_4.setBounds(52, 241, 54, 15);
		contentPanel.add(lblNewLabel_4);
		
		textField_province = new JTextField();
		textField_province.setBounds(116, 38, 211, 21);
		contentPanel.add(textField_province);
		textField_province.setColumns(10);
		
		textField_city = new JTextField();
		textField_city.setBounds(116, 88, 211, 21);
		contentPanel.add(textField_city);
		textField_city.setColumns(10);
		
		textField_area = new JTextField();
		textField_area.setBounds(116, 138, 211, 21);
		contentPanel.add(textField_area);
		textField_area.setColumns(10);
		
		textField_person = new JTextField();
		textField_person.setBounds(116, 188, 211, 21);
		contentPanel.add(textField_person);
		textField_person.setColumns(10);
		
		textField_tel = new JTextField();
		textField_tel.setBounds(116, 238, 211, 21);
		contentPanel.add(textField_tel);
		textField_tel.setColumns(10);
		{
			okButton = new JButton("确定");
			okButton.setBounds(134, 311, 75, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			okButton.addActionListener(this);
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("取消");
			cancelButton.setBounds(227, 311, 75, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(this);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		textField_province.setText(address.getAddress_province());
		textField_city.setText(address.getAddress_city());
		textField_area.setText(address.getAddress_area());
		textField_person.setText(address.getAddress_person());
		textField_tel.setText(address.getAddress_tel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.cancelButton) {
			this.setVisible(false);
			this.address=null;
			return ;
		}
		else if(e.getSource()==this.okButton) {
			if(checkvalue()) {
				retvalue=true;
				modify();
				setVisible(false);
			}
		}
		// TODO Auto-generated method stub
		
	}
	public void modify() {
		try {
			LoginStart.userManager.modifyAddress(address);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Address getvalue(){
		address.setAddress_province(this.textField_province.getText().trim());
		address.setAddress_city(this.textField_city.getText().trim());
		address.setAddress_area(this.textField_area.getText().trim());
		address.setAddress_person(this.textField_person.getText().trim());
		address.setAddress_tel(this.textField_tel.getText().trim());
		return address;
	}
	private boolean checkvalue() {
		getvalue();
		if(address.getAddress_province().isEmpty()) {
			JOptionPane.showMessageDialog(this, "省份不得为空!");
			return false;
		}
		if(address.getAddress_city().isEmpty()) {
			JOptionPane.showMessageDialog(this, "城市不得为空!");
			return false;
		}
		if(address.getAddress_area().isEmpty()) {
			JOptionPane.showMessageDialog(this, "省份不得为空!");
			return false;
		}
		if(address.getAddress_person().isEmpty()) {
			JOptionPane.showMessageDialog(this, "省份不得为空!");
			return false;
		}
		if(address.getAddress_tel().isEmpty()) {
			JOptionPane.showMessageDialog(this, "省份不得为空!");
			return false;
		}
		return true;
	}
	public boolean exec() {
		return retvalue;
	}
}
