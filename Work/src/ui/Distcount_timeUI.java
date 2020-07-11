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
import model.Discount_time;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class Distcount_timeUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"优惠id","适用产品id","优惠折扣","剩余优惠数量","开始日期","结束日期"};
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
					Distcount_timeUI frame = new Distcount_timeUI();
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
	public Distcount_timeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton_back = new JButton("后退");
		toolBar.add(btnNewButton_back);
		btnNewButton_back.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		load_distcountRow();
	}
	public void load_distcountRow() {
		try {
			List<Discount_time> list =LoginStart.dsiDiscountManager.loadallDiscount_time();
			tblData=new Object[list.size()][6];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getDistcount_time_id();
				tblData[i][1]=list.get(i).getProduct_id();
				tblData[i][2]=list.get(i).getDistcount_time_price();
				tblData[i][3]=list.get(i).getDistcount_time_count();
				tblData[i][4]=list.get(i).getDistcount_time_begin();
				tblData[i][5]=list.get(i).getDistcount_time_end();
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
	public void setfmain(FMain f) {
		this.fMain=f;
	}
}
