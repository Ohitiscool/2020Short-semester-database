package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.dialect.Ingres10Dialect;

import antlr.collections.List;
import antlr.collections.impl.Vector;
import control.ProductManager;

import javax.swing.JToolBar;
import javax.swing.SingleSelectionModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import model.*;
import start.LoginStart;
import util.BaseException;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class ProductUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	JToolBar toolBar = new JToolBar();
	JButton NewButton_buy = new JButton("购买");
	JButton Button_quit = new JButton("后退");
	DefaultTableModel tabkleModel=new DefaultTableModel();
	private Object titles[]= {"商品id","类别Id","商品名称","价格","会员价","库存","规格","状态"};
	private Object tbldata[][];
	private Address address=null;
	private Coupon  coupon=null;
	private Discount_time discount_time=null;
	private Full_discount full_discount=null;
	private FMain fMain=null;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductUI frame = new ProductUI();
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
	public ProductUI() {
		setTitle("商品列表");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 763, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(NewButton_buy);
		NewButton_buy.addActionListener(this);
		Button_quit.addActionListener(this);
		
		toolBar.add(Button_quit);
		
		table = new JTable(tabkleModel);
		JScrollPane scoreBar=new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		contentPane.add(scoreBar, BorderLayout.CENTER);
		this.loadproduct();  //每次启动都生成
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
		if(e.getSource()==NewButton_buy) {
			boolean is_vip=false;
			float price=0;
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		int n=Integer.parseInt(this.tbldata[i][0].toString()); //n为产品编号                                 
		int allcount=Integer.parseInt(this.tbldata[i][5].toString());//
		System.out.println(i+"i    n"+n);
		String cString=JOptionPane.showInputDialog(this,"请输入购买的数量","");//  购买数量，对应count
		boolean isok=Pattern.compile("[a-zA-Z]").matcher(cString).find();//判断字符串是否有字母
		if(isok) {
			JOptionPane.showMessageDialog(null,  "请输入正确数字","提示",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int count=Integer.parseInt(cString); //购买数量
		if(allcount<count) {
			JOptionPane.showMessageDialog(null, "请输入小于库存数的购买数","提示",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			is_vip=LoginStart.userManager.isvip(User.currentLoginUser);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		if(is_vip) price=Float.parseFloat(this.tbldata[i][4].toString());
		else price=Float.parseFloat(this.tbldata[i][3].toString());
		float sum1=price*count;  //初始价格
		
		
		//需要线确认地址 ，再确认优惠券选择
		Chosse_Address chosse_Address=new Chosse_Address(this, "选择地址", true);
		chosse_Address.setVisible(true);
		if(chosse_Address.exec()) {
			address=chosse_Address.getaddress();
			System.out.println(address.getAddress_id()+"  "+address.getAddress_person());
		}
		else {
			return ;  //没地址就别买了
		}
		
		
		//此处要搜折扣表
		float sum2=sum1;
		try {
			discount_time=LoginStart.dsiDiscountManager.getdistcount(count, n);
			if(discount_time!=null) {
				if(count>discount_time.getDistcount_time_count()) {
					sum2=discount_time.getDistcount_time_count()*discount_time.getDistcount_time_price()*price+(count-discount_time.getDistcount_time_count())*price;
				}
				else {
					sum2=sum2*discount_time.getDistcount_time_price();
				}
			}
		} catch (BaseException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage(),"错误", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		//此处搜满折表
		float sum3=sum2;
		try {
			full_discount=LoginStart.full_distcountManager.getfulldistcount(n, sum2);
			System.out.println("full_distcount!"+full_discount);
			if(full_discount!=null) {
				sum3=sum2-full_discount.getFull_distcount_discount();
				System.out.println("fullllll   "+sum3);
			}
		} catch (BaseException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage(),"错误", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

		
		//确认优惠券，可以不用优惠券
		float sum4=sum3;
		Choose_coupon choose_coupon=new Choose_coupon(this, "选择优惠券", true,sum3);
		System.out.println("buy coupon!"+choose_coupon.usedCoupon());
		choose_coupon.setVisible(true);
		if(choose_coupon.exec()) {
			if(choose_coupon.usedCoupon()!=null) {
				coupon=choose_coupon.usedCoupon();
				sum4=sum4-choose_coupon.usedCoupon().getCoupon_sub();
			}
			this.loadproduct();
		}
		else {
			return; //没正常运行优惠券系统不给买
		}
		
		
		try {
			LoginStart.productManager.buy_product(count, n);
			this.loadproduct();
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		JOptionPane.showMessageDialog(this, "购买成功，共支付"+sum4+"元");
		System.out.println(sum1+"  "+sum2+"   "+sum3+"   "+sum4);
		
		try {
			LoginStart.orderManager.addorder(coupon, address, n, count, sum1, sum4, discount_time, full_discount);
			System.out.println(address.getAddress_id()+"   dsadsadsad");
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e1.getMessage(),"错误", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			return;
		}
		
		
	}
		
		
		
		if(e.getSource()==Button_quit) {
			fMain.setEnabled(true);
			this.setVisible(false);
		}
		// TODO Auto-generated method stub
		
	}
	public void setFmain(FMain f) {
		this.fMain=f;
	}

}
