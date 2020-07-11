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

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"满折id","适用产品id","满减门槛","优惠金额","启用日期","结束日期","描述"};
	private Object tblData[][];
	private FMain fMain=null;
	private JScrollPane jScrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUI frame = new MenuUI();
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
	public MenuUI() {
		setTitle("满折列表");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("后退");
		btnNewButton.addActionListener((e)->{
			fMain.setEnabled(true);
			this.setVisible(false);
		});
		toolBar.add(btnNewButton);
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
