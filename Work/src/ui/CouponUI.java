package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Coupon;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CouponUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"优惠券id","满减门槛","优惠金额","启用日期","结束日期"};
	private Object tblData[][];
	FMain fMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CouponUI frame = new CouponUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CouponUI() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("可领优惠券");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton_get = new JButton("领取");
		toolBar.add(btnNewButton_get);
		
		JButton btnNewButton_back = new JButton("后退");
		btnNewButton_back.addActionListener((e)->{
			fMain.setEnabled(true);
			this.setVisible(false);
		});
		toolBar.add(btnNewButton_back);
		
		tableModel.addColumn("优惠券id");
		tableModel.addColumn("优惠券描述");
		tableModel.addColumn("使用的低价");
		tableModel.addColumn("优惠额度");
		tableModel.addColumn("启用日期");
		tableModel.addColumn("截止日期");
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		
		JScrollPane scrollPane=new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		loadCouponRow();
	}
	public void loadCouponRow() {
		try {
			List<Coupon> list =LoginStart.couponManager.loadallCoupons();
			tblData=new Object[list.size()][5];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getCoupon_id();
				tblData[i][1]=list.get(i).getCoupon_between();
				tblData[i][2]=list.get(i).getCoupon_sub();
				tblData[i][3]=list.get(i).getCoupon_begin();
				tblData[i][4]=list.get(i).getCoupon_end();
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

}
