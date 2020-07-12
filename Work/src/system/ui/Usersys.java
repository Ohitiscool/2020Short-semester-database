package system.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Full_discount;
import model.User;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usersys extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"用户id","名字","性别","电话","电子邮箱","城市","注册时间","注销时间","vip"};
	private Object tblData[][];
	private SystemMain fMain=null;
	private JScrollPane jScrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usersys frame = new Usersys();
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
	public Usersys() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("注销用户");
		btnNewButton.addActionListener((e)->{
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			String id=this.tblData[i][0].toString();
			System.out.println(id+"    dsdssd");
			try {
				LoginStart.userManager.deleteuser(id);
				JOptionPane.showMessageDialog(this, "注销成功");	
				loadallUser();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("设为vip");
		btnNewButton_2.addActionListener((e)->{
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			String id=this.tblData[i][0].toString();
			try {
				LoginStart.userManager.vipuser(id);
				JOptionPane.showMessageDialog(this, "设置成功");	
				loadallUser();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
		});
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("后退");
		btnNewButton_1.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		toolBar.add(btnNewButton_1);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		loadallUser();
	}
	public void loadallUser() {
		try {
			List<User> list =LoginStart.userManager.loadalluser();
			tblData=new Object[list.size()][9];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getUser_id();
				tblData[i][1]=list.get(i).getUser_name();
				tblData[i][2]=list.get(i).getUser_sex();
				tblData[i][3]=list.get(i).getUser_tel();
				tblData[i][4]=list.get(i).getUser_email();
				tblData[i][5]=list.get(i).getUser_city();
				tblData[i][6]=list.get(i).getUser_reg_time();
				tblData[i][7]=list.get(i).getUser_end_time();
				tblData[i][8]=list.get(i).getUer_vip();
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
	public void setFmain(SystemMain f) {
		this.fMain=f;
		
	}

}
