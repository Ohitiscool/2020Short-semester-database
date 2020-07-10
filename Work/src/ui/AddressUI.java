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

import org.hibernate.persister.entity.Loadable;

import control.UserManager;
import model.Address;
import start.LoginStart;
import util.BaseException;

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import model.*;
import java.lang.*;
import model.*;
public class AddressUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	JToolBar toolBar = new JToolBar();
	JButton NewButton_add = new JButton("增加地址");
	JButton btnNewButton_delete = new JButton("删除地址");
	JButton btnNewButton_back = new JButton("后退");
	JButton btnNew_modify = new JButton("修改地址");
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object addtitles[]= {"地址id","省份","城市","地区","联系人","电话号码"};
	private Object tblData[][];
	FMain fMain=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressUI frame = new AddressUI();
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
	public AddressUI() {
		setTitle("我的地址");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //设置成只关闭此窗口
		//setDefaultCloseOperation(EXIT_ON_CLOSE); 此方法 点击关闭后全部窗口都关闭
		setBounds(100, 100, 724, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		toolBar.add(NewButton_add);
		toolBar.add(btnNewButton_delete);
		toolBar.add(btnNew_modify);
		toolBar.add(btnNewButton_back);
	
		
		table = new JTable();
		table.setModel(tableModel);
		JScrollPane scorebar=new JScrollPane(table);
		
		
		contentPane.add(scorebar, BorderLayout.CENTER);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		NewButton_add.addActionListener(this);
		btnNewButton_back.addActionListener((e)->{
			fMain.setEnabled(true);
			this.setVisible(false);
		});
		btnNew_modify.addActionListener(this);
		btnNewButton_delete.addActionListener(this);
		
		this.listallAddress();
	}
	public void listallAddress() {//重载和加载页面
		try {
			List<Address> list =LoginStart.userManager.loadAddresses();
			tblData=new Object[list.size()][6];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getAddress_id();
				tblData[i][1]=list.get(i).getAddress_province();
				tblData[i][2]=list.get(i).getAddress_city();
				tblData[i][3]=list.get(i).getAddress_area();
				tblData[i][4]=list.get(i).getAddress_person();
				tblData[i][5]=list.get(i).getAddress_tel();
			}
			tableModel.setDataVector(tblData, addtitles);
			this.table.validate();
			this.table.repaint();
			}
		 catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==NewButton_add) {
			Address_add add=new Address_add(this, "添加地址", true);
			add.addressUI=this;
			add.setVisible(true);
			if(add.exec()) {
				listallAddress();
			}
		}
		else if(e.getSource()==btnNew_modify) {
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(this.tblData[i][0].toString());
			Address address=new Address();
			address.setAddress_id(n);
			address.setAddress_province(this.tblData[i][1].toString());
			address.setAddress_city(this.tblData[i][2].toString());
			address.setAddress_area(this.tblData[i][3].toString());
			address.setAddress_person(this.tblData[i][4].toString());
			address.setAddress_tel(this.tblData[i][5].toString());
			Modify_address modify_address=new Modify_address(this, "修改地址", true, address);
			modify_address.setVisible(true);
			if(modify_address.exec()) {
				this.listallAddress();
			}
		}
		else if(e.getSource()==this.btnNewButton_delete) {
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除该类别吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				int n=Integer.parseInt(this.tblData[i][0].toString());
				try {
					LoginStart.userManager.deleteAddress(n);
					this.listallAddress();
				}catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					// TODO: handle exception
				}
			}
		}
		// TODO Auto-generated method stub
	}
	
	
	
	
	
	
	

}
