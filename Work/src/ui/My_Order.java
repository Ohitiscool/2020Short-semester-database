package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Order_info;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class My_Order extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JToolBar toolBar = new JToolBar();
	JButton btn_eva = new JButton("评价");
	JButton btnNewButton_back = new JButton("后退");
	private JScrollPane jScrollPane;
	private DefaultTableModel tableModel=new DefaultTableModel();
    private java.util.List<Order_info> list;
    private Object titles[]= {"订单id","产品id","购买数量","优惠券id","初始价格","结算价格","预计完成时间","完成时间","地址id","订单状态"};
    private Object tbldata[][];
    private JButton btn_evalution;
    private FMain fMain=null;
    private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My_Order frame = new My_Order();
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
	public My_Order() {
		setTitle("我的订单");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		btn_eva.addActionListener((e)->{
			evaluation();
			loadmyorder();
		});
		
		toolBar.add(btn_eva);
		btnNewButton_back.addActionListener((e)->{
			fMain.setEnabled(true);
			this.setVisible(false);
		});
		
		btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener((e)->{
			String id=JOptionPane.showInputDialog("请输入产品id");
			if(id.isEmpty()) {
				loadmyorder();
			}
			else {
				int n=Integer.parseInt(id);
				loadmyorder(n);
			}
		});
		toolBar.add(btnNewButton);
		
		toolBar.add(btnNewButton_back);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		loadmyorder();
	}
	public void loadmyorder()  {
		try {
			list=LoginStart.orderManager.loadmyroder();
			tbldata=new Object[list.size()][10];
			 for(int i=0;i<list.size();i++) {
				 tbldata[i][0]=list.get(i).getOrder_info_Order_id();
				 tbldata[i][1]=list.get(i).getOrder_info_product_id();
				 tbldata[i][2]=list.get(i).getOrder_info_count();
				 tbldata[i][3]=list.get(i).getOrder_info_coupon_id();
				 tbldata[i][4]=list.get(i).getOrder_info_begin_price();
				 tbldata[i][5]=list.get(i).getOrder_info_end_price();
				 tbldata[i][6]=list.get(i).getOrder_info_Order_planTime();
				 tbldata[i][7]=list.get(i).getOrder_info_order_finishTime();
				 tbldata[i][8]=list.get(i).getOder_info_address_id();
				 tbldata[i][9]=list.get(i).getOder_info_statement();
			 }
			 tableModel.setDataVector(tbldata, titles);
			 this.table.validate();
			 this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadmyorder(int n)  {
		try {
			list=LoginStart.orderManager.loadmyroder(n);
			tbldata=new Object[list.size()][10];
			 for(int i=0;i<list.size();i++) {
				 tbldata[i][0]=list.get(i).getOrder_info_Order_id();
				 tbldata[i][1]=list.get(i).getOrder_info_product_id();
				 tbldata[i][2]=list.get(i).getOrder_info_count();
				 tbldata[i][3]=list.get(i).getOrder_info_coupon_id();
				 tbldata[i][4]=list.get(i).getOrder_info_begin_price();
				 tbldata[i][5]=list.get(i).getOrder_info_end_price();
				 tbldata[i][6]=list.get(i).getOrder_info_Order_planTime();
				 tbldata[i][7]=list.get(i).getOrder_info_order_finishTime();
				 tbldata[i][8]=list.get(i).getOder_info_address_id();
				 tbldata[i][9]=list.get(i).getOder_info_statement();
			 }
			 tableModel.setDataVector(tbldata, titles);
			 this.table.validate();
			 this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void evaluation() {
		int i=this.table.getSelectedRow();
		int product_id=Integer.parseInt(this.tbldata[i][1].toString()); 
		int n=Integer.parseInt(this.tbldata[i][0].toString());//n为订单号
		EvaluateJDilog evaluateJDilog=new EvaluateJDilog(this, "评价", true, product_id, n);
		evaluateJDilog.setVisible(true);
	}
	public void setfmain(FMain f) {
		this.fMain=f;
	}

}
