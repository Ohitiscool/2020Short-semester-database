package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Coupon;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Addcoupon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_state;
	private JTextField textField_base;
	private JTextField textField_sub;
	private JTextField textField_begin;
	private JTextField textField_end;
	private Coupon coupon;
	private boolean retvalue=false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Addcoupon(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 476, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("描述");
			lblNewLabel.setBounds(91, 53, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("门槛");
			lblNewLabel_1.setBounds(91, 113, 54, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("减额");
			lblNewLabel_2.setBounds(91, 173, 54, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("启用时间");
			lblNewLabel_3.setBounds(91, 233, 54, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("过期时间");
			lblNewLabel_4.setBounds(91, 293, 54, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			textField_state = new JTextField();
			textField_state.setBounds(174, 50, 176, 21);
			contentPanel.add(textField_state);
			textField_state.setColumns(10);
		}
		{
			textField_base = new JTextField();
			textField_base.setBounds(174, 110, 176, 21);
			contentPanel.add(textField_base);
			textField_base.setColumns(10);
		}
		{
			textField_sub = new JTextField();
			textField_sub.setBounds(174, 170, 176, 21);
			contentPanel.add(textField_sub);
			textField_sub.setColumns(10);
		}
		{
			textField_begin = new JTextField();
			textField_begin.setBounds(174, 230, 176, 21);
			contentPanel.add(textField_begin);
			textField_begin.setColumns(10);
		}
		{
			textField_end = new JTextField();
			textField_end.setBounds(174, 290, 176, 21);
			contentPanel.add(textField_end);
			textField_end.setColumns(10);
		}
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(checkvaild()) {
					retvalue=true;
					addcoupon();
					this.setVisible(false);
				}
			});
			okButton.setBounds(135, 356, 74, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(276, 356, 74, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean getvalue()  {
		coupon=new Coupon();
		if(textField_state.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,  "请输入描述","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String base=textField_base.getText();
		if(base.isEmpty()||Pattern.compile("[a-zA-Z]").matcher(base).find()) {
			JOptionPane.showMessageDialog(null,  "请输入门槛数字","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String sub=textField_sub.getText();
		if(sub.isEmpty()||Pattern.compile("[a-zA-Z]").matcher(sub).find()) {
			JOptionPane.showMessageDialog(null,  "请输入减额数字","提示",JOptionPane.ERROR_MESSAGE);
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
		coupon.setCoupon_statement(textField_state.getText());
		coupon.setCoupon_between(Float.parseFloat(base));
		coupon.setCoupon_sub(Float.parseFloat(sub));
		String be=textField_begin.getText();
		String en=textField_end.getText();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try {
			java.util.Date date=sdf.parse(be);
			coupon.setCoupon_begin(new java.sql.Timestamp(date.getTime()));
			date=sdf.parse(en);
			coupon.setCoupon_end(new java.sql.Timestamp(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(coupon.getCoupon_begin()==null||coupon.getCoupon_end()==null) {
			JOptionPane.showMessageDialog(null,  "时间字段设置错误","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkvaild() {
		return getvalue();
	}
	public boolean exec() {
		return retvalue;
	}
	public void addcoupon() {
		try {
			LoginStart.couponManager.addcoupon(coupon);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
