package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderSearch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_product;
	private JTextField textField_user;
	private String productid;
	private String userid;
	private boolean retval=false;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public OrderSearch(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 452, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品id");
		lblNewLabel.setBounds(75, 55, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户id");
		lblNewLabel_1.setBounds(75, 135, 54, 15);
		contentPanel.add(lblNewLabel_1);
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(checkval()) {
					retval=true;
					this.setVisible(false);
				}
			});
			okButton.setBounds(137, 217, 85, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(253, 217, 85, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		textField_product = new JTextField();
		textField_product.setBounds(156, 52, 161, 21);
		contentPanel.add(textField_product);
		textField_product.setColumns(10);
		
		textField_user = new JTextField();
		textField_user.setBounds(156, 132, 161, 21);
		contentPanel.add(textField_user);
		textField_user.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean checkval() {
		return getvalue();
	}
	public boolean getvalue() {
		productid=textField_product.getText();
		userid=textField_user.getText();
		if(productid.isEmpty()&&userid.isEmpty()) return true;
		else return true;
	}
	public boolean exec() {
		return retval;
	}

	public String getProductid() {
		return productid;
	}

	public String getUserid() {
		return userid;
	}
	
}
