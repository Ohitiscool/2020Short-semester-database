package system.ui;

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
import ui.FMain;
import util.BaseException;

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderSys extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane jScrollPane;
	private DefaultTableModel tableModel=new DefaultTableModel();
    private java.util.List<Order_info> list;
    private Object titles[]= {"订单id","产品id","用户id","购买数量","限时优惠id","初始价格","结算价格","优惠券id","满折id","预计完成时间","完成时间","地址id","订单状态"};
    private Object tbldata[][];
    private JButton btn_evalution;
    private SystemMain fMain=null;
    private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSys frame = new OrderSys();
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
	public OrderSys() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 929, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("完成订单");
		btnNewButton.addActionListener((e)->{
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(this.tbldata[i][0].toString());
			try {
				LoginStart.orderManager.finishorder(n);
				loadmyorder();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		toolBar.add(btnNewButton);
		
		btnNewButton_2 = new JButton("搜索");
		btnNewButton_2.addActionListener((e)->{
			OrderSearch orderSearch=new OrderSearch(this, "搜索信息输入", true);
			orderSearch.setVisible(true);
			String product;
			String user;
			if(orderSearch.exec()) {
				product=orderSearch.getProductid();
				user=orderSearch.getUserid();
				if(product.isEmpty()&&user.isEmpty()) loadmyorder();
				else if(user.isEmpty()&&!product.isEmpty()) {
					int n=Integer.parseInt(product);
					loadmyorder(n);
				}
				else if(!user.isEmpty()&&!product.isEmpty()) {
					int n=Integer.parseInt(product);
					loadmyorder(n,user);
				}
				else if(!user.isEmpty()&&product.isEmpty()) {
					loadmyorder(user);
				}
			}
//			String id=JOptionPane.showInputDialog("请输入产品id");
//			if(id.isEmpty()) {
//				loadmyorder();
//			}
//			else {
//				int n=Integer.parseInt(id);
//				loadmyorder(n);
//			}
		});
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("后退");
		toolBar.add(btnNewButton_1);
		btnNewButton_1.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		loadmyorder();
	}
	public void loadmyorder()  {
		try {
			list=LoginStart.orderManager.loadroder();
			tbldata=new Object[list.size()][13];
			 for(int i=0;i<list.size();i++) {
				 tbldata[i][0]=list.get(i).getOrder_info_Order_id();
				 tbldata[i][1]=list.get(i).getOrder_info_product_id();
				 tbldata[i][2]=list.get(i).getOrder_info_user_id();
				 tbldata[i][3]=list.get(i).getOrder_info_count();
				 tbldata[i][4]=list.get(i).getOrder_info_discount_time_id();
				 tbldata[i][5]=list.get(i).getOrder_info_begin_price();
				 tbldata[i][6]=list.get(i).getOrder_info_end_price();
				 tbldata[i][7]=list.get(i).getOrder_info_coupon_id();
				 tbldata[i][8]=list.get(i).getOder_info_fulldiscount_ID();
				 tbldata[i][9]=list.get(i).getOrder_info_Order_planTime();
				 tbldata[i][10]=list.get(i).getOrder_info_order_finishTime();
				 tbldata[i][11]=list.get(i).getOder_info_address_id();
				 tbldata[i][12]=list.get(i).getOder_info_statement();
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
			list=LoginStart.orderManager.loadroder(n);
			tbldata=new Object[list.size()][13];
			 for(int i=0;i<list.size();i++) {
				 tbldata[i][0]=list.get(i).getOrder_info_Order_id();
				 tbldata[i][1]=list.get(i).getOrder_info_product_id();
				 tbldata[i][2]=list.get(i).getOrder_info_user_id();
				 tbldata[i][3]=list.get(i).getOrder_info_count();
				 tbldata[i][4]=list.get(i).getOrder_info_discount_time_id();
				 tbldata[i][5]=list.get(i).getOrder_info_begin_price();
				 tbldata[i][6]=list.get(i).getOrder_info_end_price();
				 tbldata[i][7]=list.get(i).getOrder_info_coupon_id();
				 tbldata[i][8]=list.get(i).getOder_info_fulldiscount_ID();
				 tbldata[i][9]=list.get(i).getOrder_info_Order_planTime();
				 tbldata[i][10]=list.get(i).getOrder_info_order_finishTime();
				 tbldata[i][11]=list.get(i).getOder_info_address_id();
				 tbldata[i][12]=list.get(i).getOder_info_statement();
			 }
			 tableModel.setDataVector(tbldata, titles);
			 this.table.validate();
			 this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadmyorder(String userid)  {
		try {
			list=LoginStart.orderManager.loadroder(userid);
			tbldata=new Object[list.size()][13];
			 for(int i=0;i<list.size();i++) {
				 tbldata[i][0]=list.get(i).getOrder_info_Order_id();
				 tbldata[i][1]=list.get(i).getOrder_info_product_id();
				 tbldata[i][2]=list.get(i).getOrder_info_user_id();
				 tbldata[i][3]=list.get(i).getOrder_info_count();
				 tbldata[i][4]=list.get(i).getOrder_info_discount_time_id();
				 tbldata[i][5]=list.get(i).getOrder_info_begin_price();
				 tbldata[i][6]=list.get(i).getOrder_info_end_price();
				 tbldata[i][7]=list.get(i).getOrder_info_coupon_id();
				 tbldata[i][8]=list.get(i).getOder_info_fulldiscount_ID();
				 tbldata[i][9]=list.get(i).getOrder_info_Order_planTime();
				 tbldata[i][10]=list.get(i).getOrder_info_order_finishTime();
				 tbldata[i][11]=list.get(i).getOder_info_address_id();
				 tbldata[i][12]=list.get(i).getOder_info_statement();
			 }
			 tableModel.setDataVector(tbldata, titles);
			 this.table.validate();
			 this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadmyorder(int n,String userid)  {
		try {
			list=LoginStart.orderManager.loadroder(n,userid);
			tbldata=new Object[list.size()][13];
			 for(int i=0;i<list.size();i++) {
				 tbldata[i][0]=list.get(i).getOrder_info_Order_id();
				 tbldata[i][1]=list.get(i).getOrder_info_product_id();
				 tbldata[i][2]=list.get(i).getOrder_info_user_id();
				 tbldata[i][3]=list.get(i).getOrder_info_count();
				 tbldata[i][4]=list.get(i).getOrder_info_discount_time_id();
				 tbldata[i][5]=list.get(i).getOrder_info_begin_price();
				 tbldata[i][6]=list.get(i).getOrder_info_end_price();
				 tbldata[i][7]=list.get(i).getOrder_info_coupon_id();
				 tbldata[i][8]=list.get(i).getOder_info_fulldiscount_ID();
				 tbldata[i][9]=list.get(i).getOrder_info_Order_planTime();
				 tbldata[i][10]=list.get(i).getOrder_info_order_finishTime();
				 tbldata[i][11]=list.get(i).getOder_info_address_id();
				 tbldata[i][12]=list.get(i).getOder_info_statement();
			 }
			 tableModel.setDataVector(tbldata, titles);
			 this.table.validate();
			 this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setFrame(SystemMain  f) {
		this.fMain=f;
	}

}
