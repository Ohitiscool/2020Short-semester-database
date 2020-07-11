package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.criterion.BetweenExpression;

import model.Address;
import model.Coupon;
import start.LoginStart;
import util.BaseException;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class Choose_coupon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"优惠券id","描述","满减门槛","优惠金额","启用日期","结束日期"};
	private Object tblData[][];
	private List<Coupon>  list;
	private Coupon coupon=null;
	private boolean retvalue=false;
	private float sum=0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Choose_coupon(Frame f,String title,boolean model,float sum1) {
		super(f,title,model);
		setTitle(title);
		sum=sum1;
		setBounds(100, 100, 703, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(tableModel);
		JScrollPane jScrollPane=new JScrollPane(table);
		contentPanel.add(jScrollPane, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton okButton_OK = new JButton("确定");
		okButton_OK.addActionListener((e)->{
				if(checkVaild()) {
						retvalue=true;
						this.setVisible(false);
				}
			  });
		okButton_OK.setActionCommand("OK");
		buttonPane.add(okButton_OK);
		getRootPane().setDefaultButton(okButton_OK);
			
			
			
			
		JButton cancelButton = new JButton("取消");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener((e)->{
			this.setVisible(false);
		});
		
		loadcoupon();
	}
	public void loadcoupon() {
		try {
			list =LoginStart.couponManager.loadmyCoupons(sum);
			tblData=new Object[list.size()][6];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getCoupon_id();
				tblData[i][1]=list.get(i).getCoupon_statement();
				tblData[i][2]=list.get(i).getCoupon_between();
				tblData[i][3]=list.get(i).getCoupon_sub();
				tblData[i][4]=list.get(i).getCoupon_begin();
				tblData[i][5]=list.get(i).getCoupon_end();
			}
			tableModel.setDataVector(tblData, titles);
			this.table.validate();
			this.table.repaint();
			}
		 catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean getvalue() {
		if(list.size()!=0) {
			System.out.println("优惠券数量"+list.size());
			int i=this.table.getSelectedRow();
		    if(i<0) {
		    	JOptionPane.showMessageDialog(null, "不使用优惠券","提示",JOptionPane.WARNING_MESSAGE);
				return true;
		    	}
		    int n=Integer.parseInt(this.tblData[i][0].toString());
		    System.out.println("优惠券id   "+n);
		    try {
		    	coupon=LoginStart.couponManager.usemycoupon(n,sum);
		    	System.out.println(coupon);
		    	JOptionPane.showMessageDialog(null, "使用成功");
		    	return true;
		    	} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    	}
			}
		return true;
	}
	public boolean checkVaild() {
		return getvalue();
	}
	public boolean exec() {
		return retvalue;
	}
	public Coupon usedCoupon() {
		return coupon;
	}


}
