package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Product;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductModify extends JDialog {
	public ProductModify() {
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_name;
	private JTextField textField_price;
	private JTextField textField_vip;
	private JTextField textField_stock;
	private JTextField textField_format;
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private JComboBox<String> comboBox=new JComboBox<String>();
	private Product product;
	private boolean retvalue=false;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ProductModify(JFrame f,String s,boolean b,Product products) {
		super(f,s,b);
		this.product=products;
		setBounds(100, 100, 409, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("产品名字");
			lblNewLabel.setBounds(55, 53, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("产品价格");
			lblNewLabel_1.setBounds(55, 103, 54, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("会员价");
			lblNewLabel_2.setBounds(55, 153, 54, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("库存");
			lblNewLabel_3.setBounds(55, 203, 54, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("规格");
			lblNewLabel_4.setBounds(55, 253, 54, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel label = new JLabel("状态");
			label.setBounds(55, 305, 54, 15);
			contentPanel.add(label);
		}
		{
			textField_name = new JTextField();
			textField_name.setBounds(136, 50, 131, 21);
			contentPanel.add(textField_name);
			textField_name.setColumns(10);
		}
		{
			textField_price = new JTextField();
			textField_price.setBounds(136, 100, 131, 21);
			contentPanel.add(textField_price);
			textField_price.setColumns(10);
		}
		{
			textField_vip = new JTextField();
			textField_vip.setBounds(136, 150, 131, 21);
			contentPanel.add(textField_vip);
			textField_vip.setColumns(10);
		}
		{
			textField_stock = new JTextField();
			textField_stock.setBounds(136, 200, 131, 21);
			contentPanel.add(textField_stock);
			textField_stock.setColumns(10);
		}
		{
			textField_format = new JTextField();
			textField_format.setBounds(136, 250, 131, 21);
			contentPanel.add(textField_format);
			textField_format.setColumns(10);
		}
		{
			comboBox.setBounds(136, 300, 131, 21);
			contentPanel.add(comboBox);
			comboBox.addItem("下架");
			comboBox.addItem("上架");
		}
		{
			okButton.addActionListener((e)->{
				if(checkvalue()) {
					retvalue=true;
					modify();
					this.setVisible(false);
				}
			});
			okButton.setBounds(107, 362, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(200, 362, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		textField_name.setText(product.getProduct_name());
		textField_price.setText(product.getProduct_price()+"");
		textField_vip.setText(product.getProduct_vip_price()+"");
		textField_stock.setText(product.getProduct_stock()+"");
		textField_format.setText(product.getProduct_format());
	}
	public String jud() {
		String state=(String)comboBox.getSelectedItem();
		if(state.equals("上架")) return "In the sale";
		else return "sold out";
	}
	public boolean getvalue() {
		product.setProduct_name(textField_name.getText().trim());
		String price=textField_price.getText().trim();
		String vip=textField_vip.getText().trim();
		String stock=textField_stock.getText().trim();
		product.setProduct_statement(jud());
		product.setProduct_format(textField_format.getText().trim());
		if(Pattern.compile("[a-zA-Z]").matcher(price).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正常价格","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(Pattern.compile("[a-zA-Z]").matcher(vip).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正常价格","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(Pattern.compile("[a-zA-Z]").matcher(stock).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正常库存","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		product.setProduct_price(Float.parseFloat(price));
		product.setProduct_vip_price(Float.parseFloat(vip));
		product.setProduct_stock(Integer.parseInt(stock));
		return true;
	}
	
	public boolean checkvalue() {
		return getvalue();
	}
	public Product getProduct() {
		return product;
	}
	public boolean exec() {
		return retvalue;
	}
	public void modify() {
		try {
			LoginStart.productManager.modifyroduct(product);
			System.out.println("modify! "+product.getProduct_id());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
