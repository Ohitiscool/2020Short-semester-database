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
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import model.Full_discount;
import model.Product;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFullDist extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_base;
	private JTextField textField_sub;
	private JTextField textField_begin;
	private JTextField textField_end;
	private Full_discount full_discount;
	private Boolean retvalue=false;
	private JTextField textField_state;
	private int id=0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AddFullDist(JFrame f,String s,boolean b,int n) {
		super(f,s,b);
		id=n;
		setBounds(100, 100, 401, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("满减门槛");
			lblNewLabel.setBounds(53, 53, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("满减金额");
			lblNewLabel_1.setBounds(53, 103, 54, 15);
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
			JLabel lblNewLabel_4 = new JLabel("描述");
			lblNewLabel_4.setBounds(53, 253, 54, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			textField_base = new JTextField();
			textField_base.setBounds(117, 50, 157, 21);
			contentPanel.add(textField_base);
			textField_base.setColumns(10);
		}
		{
			textField_sub = new JTextField();
			textField_sub.setBounds(117, 100, 157, 21);
			contentPanel.add(textField_sub);
			textField_sub.setColumns(10);
		}
		{
			textField_begin = new JTextField();
			textField_begin.setBounds(117, 150, 157, 21);
			contentPanel.add(textField_begin);
			textField_begin.setColumns(10);
		}
		{
			textField_end = new JTextField();
			textField_end.setBounds(117, 200, 157, 21);
			contentPanel.add(textField_end);
			textField_end.setColumns(10);
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
			okButton.setBounds(117, 304, 57, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(204, 304, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			textField_state = new JTextField();
			textField_state.setBounds(117, 250, 157, 21);
			contentPanel.add(textField_state);
			textField_state.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean getvalue() {
		full_discount=new Full_discount();
		String base=textField_base.getText();
		if(base.isEmpty()||Pattern.compile("[a-zA-Z]").matcher(base).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正确满减门槛","提示",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String sub=textField_sub.getText();
		if(sub.isEmpty()||Pattern.compile("[a-zA-Z]").matcher(sub).find()) {
			JOptionPane.showMessageDialog(null,  "请输入正确满减金额","提示",JOptionPane.ERROR_MESSAGE);
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
		full_discount.setFull_distcount_base(Float.parseFloat(base));
		full_discount.setFull_distcount_discount(Float.parseFloat(sub));
		full_discount.setFull_distcount_statement(textField_state.getText());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try {
			java.util.Date date=sdf.parse(beS);
			full_discount.setFull_distcount_begin_id(new java.sql.Timestamp(date.getTime()));
			date=sdf.parse(enS);
			full_discount.setFull_distcount_end_id(new java.sql.Timestamp(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(full_discount.getFull_distcount_statement()+"   yiduiluanqibazao");
		if(full_discount.getFull_distcount_begin_id()==null||full_discount.getFull_distcount_end_id()==null) {
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
	public void add() {
		try {
			LoginStart.full_distcountManager.addfulldistcount(full_discount, id);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
