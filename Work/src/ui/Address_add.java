package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import control.UserManager;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import start.*;
import util.BaseException;
import util.BussinessException;
import model.*;
public class Address_add extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_province;
	private JTextField textField_city;
	private JTextField textField_area;
	private JTextField textField_person;
	private JTextField textField_tel;
	AddressUI addressUI;
	private boolean retvalue=false;
	Address address2;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	private void add(){
		String province=textField_province.getText();
		String city=textField_city.getText();
		String area=textField_area.getText();
		String person=textField_person.getText();
		String tel=textField_tel.getText();
		try {
			address2=LoginStart.userManager.add_address(province, city, area, person, tel);
			this.setVisible(false);
		} catch (BussinessException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public Address_add(Frame f,String title,boolean model) {
		super(f,title,model);
		setTitle("添加地址");
		setBounds(100, 100, 414, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("省份");
		lblNewLabel.setBounds(44, 53, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("城市");
		lblNewLabel_1.setBounds(44, 103, 54, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("地区");
		lblNewLabel_2.setBounds(44, 153, 54, 15);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("联系人姓名");
		lblNewLabel_3.setBounds(44, 203, 71, 15);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("电话号码");
		lblNewLabel_4.setBounds(44, 253, 54, 15);
		contentPanel.add(lblNewLabel_4);
		
		textField_province = new JTextField();
		textField_province.setBounds(125, 50, 175, 21);
		contentPanel.add(textField_province);
		textField_province.setColumns(10);
		
		textField_city = new JTextField();
		textField_city.setBounds(125, 100, 175, 21);
		contentPanel.add(textField_city);
		textField_city.setColumns(10);
		
		textField_area = new JTextField();
		textField_area.setBounds(125, 150, 175, 21);
		contentPanel.add(textField_area);
		textField_area.setColumns(10);
		
		textField_person = new JTextField();
		textField_person.setBounds(125, 200, 175, 21);
		contentPanel.add(textField_person);
		textField_person.setColumns(10);
		
		textField_tel = new JTextField();
		textField_tel.setBounds(125, 250, 175, 21);
		contentPanel.add(textField_tel);
		textField_tel.setColumns(10);
		JButton okButton = new JButton("确认");
		okButton.addActionListener((e)->{
				if(checkValid()) {
					retvalue=true;
					add();
					setVisible(false);
				}
			});
		
		
		okButton.setBounds(125, 304, 71, 23);
		contentPanel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
		cancelButton.setBounds(221, 304, 69, 23);
		contentPanel.add(cancelButton);
		cancelButton.setActionCommand("Cancel");

		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}
	
	
	public Address getValue() {
		Address address=new Address();
		address.setAddress_province(textField_province.getText().trim());
		address.setAddress_city(textField_city.getText().trim());
		address.setAddress_area(textField_area.getText().trim());
		address.setAddress_person(textField_person.getText().trim());
		address.setAddress_tel(textField_tel.getText().trim());
		return address;
	}
	
	public boolean checkValid() {
		Address address=getValue();
		if(address.getAddress_province().isEmpty()) {
			JOptionPane.showMessageDialog(this, "省份不得为空!");
			return false;
		}
		if(address.getAddress_city().isEmpty()) {
			JOptionPane.showMessageDialog(this, "城市不得为空!");
			return false;
		}
		if(address.getAddress_area().isEmpty()) {
			JOptionPane.showMessageDialog(this, "地区不得为空!");
			return false;
		}
		if(address.getAddress_person().isEmpty()) {
			JOptionPane.showMessageDialog(this, "联系人不得为空!");
			return false;
		}
		if(address.getAddress_tel().isEmpty()) {
			JOptionPane.showMessageDialog(this, "电话号码不得为空!");
			return false;
		}
		return true;
	}
	public boolean exec() {
		return retvalue;
	}

	
}
