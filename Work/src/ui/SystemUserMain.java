package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SystemUserMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemUserMain frame = new SystemUserMain();
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
	public SystemUserMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 509);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("产品");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_product = new JMenuItem("产品信息");
		mnNewMenu.add(mntmNewMenuItem_product);
		
		JMenuItem mntmNewMenuItem_product_buy = new JMenuItem("产品采购表");
		mnNewMenu.add(mntmNewMenuItem_product_buy);
		
		JMenuItem menuItem_typemanager = new JMenuItem("产品类别管理");
		mnNewMenu.add(menuItem_typemanager);
		
		JMenu mnNewMenu_1 = new JMenu("员工信息");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem_systemuser = new JMenuItem("管理员账号管理");
		mnNewMenu_1.add(menuItem_systemuser);
		
		JMenu mnNewMenu_order = new JMenu("订单信息");
		menuBar.add(mnNewMenu_order);
		
		JMenuItem mntmNewMenuItem_unfinished = new JMenuItem("未完成订单信息");
		mnNewMenu_order.add(mntmNewMenuItem_unfinished);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
