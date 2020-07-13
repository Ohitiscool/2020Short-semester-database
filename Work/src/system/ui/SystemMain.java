package system.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import model.*;
import ui.Distcount_timeUI;
import ui.Product_evalution;
import control.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SystemMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemMain frame = new SystemMain();
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
	public SystemMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 580);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("商品管理");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("商品类别管理");
		mntmNewMenuItem_2.addActionListener((e)->{
			TypeUI typeUI=new TypeUI();
			typeUI.setVisible(true);
			this.setEnabled(false);
			typeUI.setFmain(this);
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("商品");
		mntmNewMenuItem_3.addActionListener((e)->{
			SysProductUI sysProductUI=new SysProductUI();
			sysProductUI.setVisible(true);
			this.setEnabled(false);
			sysProductUI.setFmain(this);
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem menuItem = new JMenuItem("菜谱管理");
		menuItem.addActionListener((e)->{
			MenuAndStep menuAndStep=new MenuAndStep();
			menuAndStep.setVisible(true);
			menuAndStep.setfmain(this);
			this.setEnabled(false);
		});
		mnNewMenu.add(menuItem);
		
		JMenu mnNewMenu_1 = new JMenu("折扣管理");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("优惠券管理");
		mntmNewMenuItem_4.addActionListener((e)->{
			SysCouponUI sysCouponUI=new SysCouponUI();
			sysCouponUI.setVisible(true);
			this.setEnabled(false);
			sysCouponUI.setfmain(this);
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("满折管理");
		mntmNewMenuItem_5.addActionListener((e)->{
			SysFullDis sysFullDis=new SysFullDis();
			sysFullDis.setVisible(true);
			sysFullDis.setfmain(this);
			this.setEnabled(false);
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("限时促销");
		mntmNewMenuItem_6.addActionListener((e)->{
			Discount_timeSys discount_timeSys=new Discount_timeSys();
			discount_timeSys.setVisible(true);
			discount_timeSys.setfmain(this);
			this.setEnabled(false);
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("账号管理");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("管理员管理");
		mntmNewMenuItem.addActionListener((e)->{
			SystemList systemList=new SystemList();
			systemList.setVisible(true);
			systemList.setfmain(this);
			this.setEnabled(false);
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("用户管理");
		mntmNewMenuItem_1.addActionListener((e)->{
			Usersys usersys=new Usersys();
			usersys.setVisible(true);
			usersys.setFmain(this);
			this.setEnabled(false);
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_3 = new JMenu("订单管理");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("商品订单");
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("商品评价");
		mntmNewMenuItem_8.addActionListener((e)->{
			Product_evalution product_evalution=new Product_evalution();
			product_evalution.setVisible(true);
			this.setEnabled(false);
			product_evalution.setsys(this);
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		mntmNewMenuItem_7.addActionListener((e)->{
			OrderSys orderSys =new OrderSys();
			orderSys.setVisible(true);
			orderSys.setFrame(this);
			this.setEnabled(false);
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
