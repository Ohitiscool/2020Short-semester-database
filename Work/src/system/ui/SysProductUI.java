package system.ui;
import model.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Address;
import model.Coupon;
import model.Discount_time;
import model.Full_discount;
import model.Product;
import start.LoginStart;
import ui.FMain;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;

public class SysProductUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JButton btn_add = new JButton("添加商品");
	private JButton btn_modify = new JButton("修改商品");
	private JButton btnNewButton_back = new JButton("后退");
	DefaultTableModel tabkleModel=new DefaultTableModel();
	private Object titles[]= {"商品id","类别Id","商品名称","价格","会员价","库存","规格","状态"};
	private Object tbldata[][];
	private SystemMain fMain=null;
	private JScrollPane jScrollPane;
	private model.Type type;
	private Product product;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SysProductUI frame = new SysProductUI();
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
	public SysProductUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(btn_add);
		btn_add.addActionListener(this);
		toolBar.add(btn_modify);
		btn_modify.addActionListener(this);
		btnNewButton_back.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		
		toolBar.add(btnNewButton_back);
		
		table = new JTable(tabkleModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		
		loadproduct();
	}
	public void loadproduct() {
		try {
			java.util.List<Product> list=LoginStart.productManager.loadall();
			tbldata=new Object[list.size()][8];
				for(int i=0;i<list.size();i++) {
				tbldata[i][0]=list.get(i).getProduct_id();
				tbldata[i][1]=list.get(i).getProduct_type_id();
				tbldata[i][2]=list.get(i).getProduct_name();
				tbldata[i][3]=list.get(i).getProduct_price();
				tbldata[i][4]=list.get(i).getProduct_vip_price();
				tbldata[i][5]=list.get(i).getProduct_stock();
				tbldata[i][6]=list.get(i).getProduct_format();
				tbldata[i][7]=list.get(i).getProduct_statement();
			}
			tabkleModel.setDataVector(tbldata, titles);
			this.table.validate();
			this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_add) {
			Choose_type choose_type=new Choose_type(this, "选择商品类别", true);
			choose_type.setVisible(true);
			if(choose_type.exec()) {
				 type=choose_type.gettype();
				 System.out.println(type.getType_id()+"   "+type.getType_name());
				 JOptionPane.showMessageDialog(this, "选择成功");	
			}
			else {
				return ;
			}
			
			Product_add product_add=new Product_add(this, "商品条件", true);
			product_add.setVisible(true);
			if(product_add.exec()) {
				this.product=product_add.getProduct();
				System.out.println(product_add.exec()+"     "+product.getProduct_name());
				JOptionPane.showMessageDialog(this, "添加成功");
			}else {
				return ;
			}
			product.setProduct_type_id(type.getType_id());
			try {
				LoginStart.productManager.addproduct(product);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.loadproduct();
		
			
			
		}
		if(e.getSource()==btn_modify) {
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(this.tbldata[i][0].toString());
			product=new Product();
			product.setProduct_id(n);
			product.setProduct_type_id(Integer.parseInt(this.tbldata[i][1].toString()));
			product.setProduct_name(this.tbldata[i][2].toString());
			product.setProduct_price(Float.parseFloat(this.tbldata[i][3].toString()));
			product.setProduct_vip_price(Float.parseFloat(this.tbldata[i][4].toString()));
			product.setProduct_stock(Integer.parseInt(this.tbldata[i][5].toString()));
			product.setProduct_format(this.tbldata[i][6].toString());
			product.setProduct_statement(this.tbldata[i][7].toString());
			ProductModify productModify=new ProductModify(this, "修改信",true, product);
			productModify.setVisible(true);
			if(productModify.exec()) {
				this.loadproduct();
			}
			
			
		}
		// TODO Auto-generated method stub
		
	}
	public void setFmain(SystemMain f) {
		this.fMain=f;
		
	}

}
