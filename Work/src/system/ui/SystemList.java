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

import model.SystemUser;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SystemList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"管理员id","管理员名称"};
	private Object tblData[][];
	private SystemMain fMain=null;
	private JScrollPane jScrollPane;
	private List<SystemUser> list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemList frame = new SystemList();
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
	public SystemList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton_add = new JButton("添加管理员");
		btnNewButton_add.addActionListener((e)->{
			Addsystemuser addsystemuser=new Addsystemuser(this, "管理员信息", true);
			addsystemuser.setVisible(true);
			if(addsystemuser.exec()) {
				loadall();
			}
		});
		toolBar.add(btnNewButton_add);
		
		JButton btnNewButton = new JButton("修改我的密码");
		btnNewButton.addActionListener((e)->{
			Modifysyspwd modifysyspwd=new Modifysyspwd(this,"修改密码" ,true);
			modifysyspwd.setVisible(true);
			if(modifysyspwd.exec()) {
				JOptionPane.showMessageDialog(this, "修改成功");	
			}
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_back = new JButton("后退");
		btnNewButton_back.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		toolBar.add(btnNewButton_back);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		loadall();
	}
	public void loadall() {
		try {
			list=LoginStart.userManager.loadsystemuser();
			tblData=new Object[list.size()][2];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getSystemUser_id();
				tblData[i][1]=list.get(i).getSystemUser_name();
			}
			tableModel.setDataVector(tblData, titles);
			this.table.validate();
			this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setfmain(SystemMain f) {
		this.fMain=f;
	}

}
