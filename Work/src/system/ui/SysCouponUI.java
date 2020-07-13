package system.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Coupon;
import start.LoginStart;
import ui.FMain;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JButton;

public class SysCouponUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"优惠券id","满减门槛","优惠金额","启用日期","结束日期"};
	private Object tblData[][];
	private SystemMain fMain=null;
	private JButton btnNewButton = new JButton("添加优惠券");
	private JButton btnNewButton_1 = new JButton("删除优惠券");
	private JButton btnNewButton_2 = new JButton("后退");
	private JScrollPane jScrollPane;
	private JButton btnNewButton_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SysCouponUI frame = new SysCouponUI();
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
	public SysCouponUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		toolBar.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_3 = new JButton("搜索");
		btnNewButton_3.addActionListener((e)->{
			String base=JOptionPane.showInputDialog("请输入使用金额门槛");
			if(base.isEmpty()) {
				loadCouponRow();
			}
			else {
				float x=Float.parseFloat(base);
				loadCouponRow(x);
			}
		});
		toolBar.add(btnNewButton_3);
		
		toolBar.add(btnNewButton_2);
		btnNewButton_2.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		loadCouponRow();
	}

	public void loadCouponRow() {
	try {
		List<Coupon> list =LoginStart.couponManager.loadallCoupons_sys();
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
	public void loadCouponRow(float x) {
		try {
			List<Coupon> list =LoginStart.couponManager.loadallCoupons_sys(x);
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
	public void setfmain(SystemMain f) {
		this.fMain=f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton) {
			Addcoupon addcoupon=new Addcoupon(this,"优惠券信息",true);
			addcoupon.setVisible(true);
			if(addcoupon.exec()) {
				loadCouponRow();
			}
			System.out.println(addcoupon.exec());
		}
		if(e.getSource()==btnNewButton_1) {
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int n=Integer.parseInt(this.tblData[i][0].toString());
			try {
				LoginStart.couponManager.deletecoupon(n);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		loadCouponRow() ;
	}
}
