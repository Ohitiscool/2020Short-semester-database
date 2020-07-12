package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

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
import ui.Distcount_timeUI;
import util.BaseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AddDiscount_time extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_dis;
	private JTextField textField_count;
	private JTextField textField_begin;
	private JTextField textField_end;
	private Discount_time discount_time;
	private boolean retval=false;
	private int productid=0;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public AddDiscount_time(JFrame f,String s,boolean b,int n) {
		super(f,s,b);
		productid=n;
		setBounds(100, 100, 376, 417);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("折扣");
			lblNewLabel.setBounds(53, 50, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("适用产品数量");
			lblNewLabel_1.setBounds(53, 103, 87, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("开始时间");
			lblNewLabel_2.setBounds(53, 153, 54, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("结束时间");
			lblNewLabel_3.setBounds(53, 203, 54, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField_dis = new JTextField();
			textField_dis.setBounds(136, 50, 126, 21);
			contentPanel.add(textField_dis);
			textField_dis.setColumns(10);
		}
		{
			textField_count = new JTextField();
			textField_count.setBounds(136, 100, 126, 21);
			contentPanel.add(textField_count);
			textField_count.setColumns(10);
		}
		{
			textField_begin = new JTextField();
			textField_begin.setBounds(136, 150, 126, 21);
			contentPanel.add(textField_begin);
			textField_begin.setColumns(10);
		}
		{
			textField_end = new JTextField();
			textField_end.setBounds(136, 200, 126, 21);
			contentPanel.add(textField_end);
			textField_end.setColumns(10);
		}
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(checkvaild()) {
					retval=true;
					add();
					this.setVisible(false);
				}
			});
			okButton.setBounds(89, 275, 87, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(199, 275, 87, 23);
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
		discount_time=new Discount_time();
		String price=textField_dis.getText();
		if(price.isEmpty()||Pattern.compile("[a-zA-Z]").matcher(price).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正常折扣数字","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String count=textField_count.getText();
		if(count.isEmpty()||Pattern.compile("[a-zA-Z]").matcher(count).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正常数量数字","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String beS=textField_begin.getText();
		if(beS.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "开始时间不得为空","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String enS=textField_end.getText();
		if(enS.isEmpty()) {
			JOptionPane.showMessageDialog(null,  "结束时间不得为空","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		discount_time.setDistcount_time_price(Float.parseFloat(price));
		discount_time.setDistcount_time_count(Integer.parseInt(count));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try {
			java.util.Date date=sdf.parse(beS);
			discount_time.setDistcount_time_begin(new java.sql.Timestamp(date.getTime()));
			System.out.println(date +"     testdate1   "+discount_time.getDistcount_time_begin());
			date=sdf.parse(enS);
			discount_time.setDistcount_time_end(new java.sql.Timestamp(date.getTime()));
			System.out.println(date +"     testdate2  "+discount_time.getDistcount_time_end());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(discount_time.getDistcount_time_begin()==null||discount_time.getDistcount_time_end()==null) {
			JOptionPane.showMessageDialog(null,  "时间字段设置错误","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkvaild() {
		return getvalue();
	}
	public boolean exec() {
		return retval;
	}
	public void add() {
		try {
			LoginStart.dsiDiscountManager.addDiscount_time(discount_time, productid);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
