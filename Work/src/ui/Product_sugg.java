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
import model.Product;
import start.LoginStart;
import util.BaseException;

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Product_sugg extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object titles[]= {"商品id","类别Id","商品名称","价格","会员价","库存","规格","状态"};
	private Object tbldata[][];
	private FMain fMain=null;
	private JScrollPane jScrollPane=new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_sugg frame = new Product_sugg();
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
	public Product_sugg() {
		setTitle("商品推荐");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("后退");
		btnNewButton.addActionListener((e)->{
			fMain.setEnabled(true);
			this.setVisible(false);
		});
		toolBar.add(btnNewButton);
		loadproduct();
	}
	public void loadproduct() {
		try {
			java.util.List<Product> list=LoginStart.productManager.load_p_sugg();
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
			tableModel.setDataVector(tbldata, titles);
			this.table.validate();
			this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setfmain(FMain f) {
		this.fMain=f;
	}

}
