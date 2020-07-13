package system.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PRIVATE_MEMBER;

import model.Full_discount;
import model.Product;
import start.LoginStart;
import ui.FMain;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class SysFullDis extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"满折id","适用产品id","满减门槛","优惠金额","启用日期","结束日期","描述"};
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
					SysFullDis frame = new SysFullDis();
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
	public SysFullDis() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener((e)->{
			Choose_Product choose_Product=new Choose_Product(this,"选择商品",true);
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
			
			
			 AddFullDist addFullDist=new AddFullDist(this, "满减信息", true, product.getProduct_id());
			 addFullDist.setVisible(true);
			 if(addFullDist.exec()) {
				 loadManzheRow();
			 }
			
			
			
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("终止");
		btnNewButton_1.addActionListener((e)->{
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(this.tblData[i][0].toString());
			try {
				LoginStart.full_distcountManager.deletefulldistcount(n);
				loadManzheRow();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		JButton btnNewButton_3 = new JButton("搜索");
		btnNewButton_3.addActionListener((e)->{
			String id=JOptionPane.showInputDialog("请输入产品id");
			if(id.isEmpty()) {
				loadManzheRow();
			}
			else {
				int n=Integer.parseInt(id);
				loadManzheRow(n);
			}
		});
		toolBar.add(btnNewButton_3);
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("后退");
		btnNewButton_2.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		toolBar.add(btnNewButton_2);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		loadManzheRow();
	}
	public void loadManzheRow() {
		try {
			List<Full_discount> list =LoginStart.full_distcountManager.loadallfull_sys();
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
	public void loadManzheRow(int n) {
		try {
			List<Full_discount> list =LoginStart.full_distcountManager.loadallfull_sys(n);
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
	public void setfmain(SystemMain f) {
		this.fMain=f;
	}


}
