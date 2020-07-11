package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Coupon;
import model.Product_eva;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Product_evalution extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"评价id","用户id","商品id","评价时间","评级","评论"};
	private Object tblData[][];
	private FMain fMain=null;
	private JScrollPane jScrollPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_evalution frame = new Product_evalution();
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
	public Product_evalution() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 598);
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
		toolBar.add(btnNewButton);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		load_product_eva();
	}
	public void load_product_eva() {
		try {
			List<Product_eva> list =LoginStart.orderManager.load_produ_Evas();
			tblData=new Object[list.size()][6];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getProduct_Evaluation_id();
				tblData[i][1]=list.get(i).getProduct_Evaluation_user_id();
				tblData[i][2]=list.get(i).getProduct_Evaluation_product_id();
				tblData[i][3]=list.get(i).getProduct_Evaluation_time();
				tblData[i][4]=list.get(i).getProduct_Evaluation_level();
				tblData[i][5]=list.get(i).getProduct_Evaluation_imag();
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
