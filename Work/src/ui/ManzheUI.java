package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Coupon;
import model.Full_discount;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManzheUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"满折id","适用产品id","满减门槛","优惠金额","启用日期","结束日期","描述"};
	private Object tblData[][];
	private FMain fMain=null;
	private JScrollPane jScrollPane;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManzheUI frame = new ManzheUI();
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
	public ManzheUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("后退");
		btnNewButton.addActionListener((e)->{
			fMain.setEnabled(true);
			this.setVisible(false);
		});
		
		btnNewButton_1 = new JButton("搜索");
		btnNewButton_1.addActionListener((e)->{
			String s=JOptionPane.showInputDialog("请输入产品id");
			if(s.isEmpty()) {
				loadManzheRow();
			}
			else {
				int x=Integer.parseInt(s);
				loadManzheRow(x);
			}
		});
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		loadManzheRow();
	}
	public void loadManzheRow() {
		try {
			List<Full_discount> list =LoginStart.full_distcountManager.loadallfull();
			tblData=new Object[list.size()][7];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getFull_distcount_id();
				tblData[i][1]=list.get(i).getFull_distcount_product_id();
				tblData[i][2]=list.get(i).getFull_distcount_base();
				tblData[i][3]=list.get(i).getFull_distcount_discount();
				tblData[i][4]=list.get(i).getFull_distcount_begin_id();
				tblData[i][5]=list.get(i).getFull_distcount_end_id();
				tblData[i][6]=list.get(i).getFull_distcount_statement();
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
	public void loadManzheRow(int x) {
		try {
			List<Full_discount> list =LoginStart.full_distcountManager.loadallfull(x);
			tblData=new Object[list.size()][7];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getFull_distcount_id();
				tblData[i][1]=list.get(i).getFull_distcount_product_id();
				tblData[i][2]=list.get(i).getFull_distcount_base();
				tblData[i][3]=list.get(i).getFull_distcount_discount();
				tblData[i][4]=list.get(i).getFull_distcount_begin_id();
				tblData[i][5]=list.get(i).getFull_distcount_end_id();
				tblData[i][6]=list.get(i).getFull_distcount_statement();
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
