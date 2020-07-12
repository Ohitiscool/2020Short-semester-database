package system.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Discount_time;
import start.LoginStart;
import ui.FMain;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.*;
public class Discount_timeSys extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"优惠id","适用产品id","优惠折扣","剩余优惠数量","开始日期","结束日期"};
	private Object tblData[][];
	private SystemMain fMain=null;
	private JScrollPane jScrollPane;
	private Product product;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Discount_timeSys frame = new Discount_timeSys();
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
	public Discount_timeSys() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton_add = new JButton("添加");
		btnNewButton_add.addActionListener((e)->{
			//选择商品
			Choose_Product choose_Product=new Choose_Product(this, "选择商品", true);
			choose_Product.setVisible(true);
			System.out.println(choose_Product.exec());
			if(choose_Product.exec()) {
				product=choose_Product.getProduct();
				 System.out.println(product.getProduct_id()+"   "+product.getProduct_name());
				 JOptionPane.showMessageDialog(this, "选择成功");	
			}
			else {
				return ;
			}
			
			AddDiscount_time addDiscount_time=new AddDiscount_time(this, "限时折扣信息", true, product.getProduct_id());
			addDiscount_time.setVisible(true);
			if(addDiscount_time.exec()) {
				load_distcountRow();
			}
			
			
		});
		toolBar.add(btnNewButton_add);
		
		JButton btnNewButton_delete = new JButton("终止");
		btnNewButton_delete.addActionListener((e)->{
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(this.tblData[i][0].toString());
			try {
				LoginStart.dsiDiscountManager.deleteDiscount_time(n);
				load_distcountRow();
				JOptionPane.showMessageDialog(this, "终止成功");	
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		toolBar.add(btnNewButton_delete);
		
		JButton btnNewButton_back = new JButton("后退");
		btnNewButton_back.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		toolBar.add(btnNewButton_back);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		load_distcountRow();
	}
	public void load_distcountRow() {
		try {
			List<Discount_time> list =LoginStart.dsiDiscountManager.loadallDiscount_time_sys();
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
	public void setfmain(SystemMain f) {
		this.fMain=f;
	}
	
	

}
