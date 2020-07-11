package ui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import control.ProductManager;
import model.Product;
import model.User;
import util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;


public class FMain extends JFrame {

	private JPanel contentPane;
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FMain frame = new FMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Product专用
	//
	
	/**
	 * Create the frame.
	 */
	public FMain() {
		setForeground(Color.BLACK);
		setTitle("你好");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 556);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Menu_product = new JMenu("商品信息");
		Menu_product.setForeground(Color.BLACK);
		menuBar.add(Menu_product);
		
		JMenuItem menuItem_product = new JMenuItem("商品");
		menuItem_product.addActionListener((e)->{
				ProductUI productUI=new ProductUI();
				productUI.setVisible(true);
				this.setEnabled(false);
				productUI.setFmain(this);
		});
		Menu_product.add(menuItem_product);
		
		JMenuItem menuItem_menu = new JMenuItem("菜谱");
		Menu_product.add(menuItem_menu);
		
		JMenuItem menuItem_product_sugg = new JMenuItem("商品推荐");
		menuItem_product_sugg.addActionListener((e)->{
			Product_sugg product_sugg=new Product_sugg();
			product_sugg.setfmain(this);
			this.setEnabled(false);
			product_sugg.setVisible(true);
		});
		Menu_product.add(menuItem_product_sugg);
		
		JMenuItem menuItem_menu_sugg = new JMenuItem("菜谱推荐");
		Menu_product.add(menuItem_menu_sugg);
		
		JMenuItem menuItem_product_statement = new JMenuItem("商品评价");
		menuItem_product_statement.addActionListener((e)->{
			Product_evalution product_evalution=new Product_evalution();
			product_evalution.setVisible(true);
			this.setEnabled(false);
			product_evalution.setfmain(this);
		});
		Menu_product.add(menuItem_product_statement);
		
		JMenu Menu_coupon = new JMenu("优惠券");
		Menu_coupon.setForeground(Color.BLACK);
		menuBar.add(Menu_coupon);
		
		JMenuItem menuItem_coupon = new JMenuItem("可领优惠券");
		menuItem_coupon.addActionListener((e)->{
			CouponUI couponUI=new CouponUI();
			couponUI.setVisible(true);
			this.setEnabled(false);
			couponUI.setfmain(this);
		});
		Menu_coupon.add(menuItem_coupon);
		
		JMenu Menu_discount = new JMenu("折扣");
		Menu_discount.setForeground(Color.BLACK);
		menuBar.add(Menu_discount);
		
		JMenuItem menuItem_distcount = new JMenuItem("限时促销");
		menuItem_distcount.addActionListener((e)->{
			Distcount_timeUI distcount_timeUI=new Distcount_timeUI();
			distcount_timeUI.setVisible(true);
			this.setEnabled(false);
			distcount_timeUI.setfmain(this);
		});
		Menu_discount.add(menuItem_distcount);
		
		JMenuItem Menuitem_manzhe = new JMenuItem("满折优惠");
		Menuitem_manzhe.addActionListener((e)->{
			ManzheUI manzheUI=new ManzheUI();
			this.setEnabled(false);
			manzheUI.setfmain(this);
			manzheUI.setVisible(true);
		});
		Menu_discount.add(Menuitem_manzhe);
		
		JMenu Menu_mine = new JMenu("我的");
		Menu_mine.setForeground(Color.BLACK);
		menuBar.add(Menu_mine);
		
		JMenuItem Menuitem_address = new JMenuItem("我的地址");
		Menuitem_address.addActionListener((e)->{
			AddressUI addressUI=new AddressUI();
			addressUI.setVisible(true);
			this.setEnabled(false);  
			addressUI.setfmain(this);
		});
		Menu_mine.add(Menuitem_address);
		
		JMenuItem Menuitem_order = new JMenuItem("我的订单");
		Menuitem_order.addActionListener((e)->{
			My_Order my_Order=new My_Order();
			my_Order.setfmain(this);
			this.setEnabled(false);
			my_Order.setVisible(true);
		});
		Menu_mine.add(Menuitem_order);
		
		JMenuItem Menuitem_mine_coupon = new JMenuItem("我的优惠券");
		Menuitem_mine_coupon.addActionListener((e)->{
			Mycoupon mycoupon=new Mycoupon();
			this.setEnabled(false);
			mycoupon.setfmain(this);
			mycoupon.setVisible(true);
		});
		Menu_mine.add(Menuitem_mine_coupon);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		DefaultTableModel produ_TableModel=new DefaultTableModel();
		
		
	}
	public FMain getFMain(FMain fMain) {
		return this;
	}

	



}
