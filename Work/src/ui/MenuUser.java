package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.*;
import model.Menu_step;
import start.LoginStart;
import system.ui.MenuAndStep;
import system.ui.SystemMain;
import util.BaseException;

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import control.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuUser extends JFrame {

	private JPanel contentPane;
	private JTable table_menu;
	private JTable table_step;
	DefaultTableModel tableModel_menu=new DefaultTableModel();
	DefaultTableModel tableModel_step=new DefaultTableModel();
	private Object titles_menu[]= {"菜谱号","菜谱名"};
	private Object titles_step[]= {"步骤号","菜谱号","食材","操作"};
	private Object tbldate_menu[][];
	private Object tbldate_step[][];
	private JScrollPane js_menu;
	private JScrollPane js_step;
	private List<Menu> list_menu;
	private List<Menu_step> list_step;
	private Menu menu;
	private FMain fMain;
	private JToolBar toolBar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUser frame = new MenuUser();
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
	public MenuUser() {
		setTitle("菜谱");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table_menu = new JTable(tableModel_menu);
		js_menu=new JScrollPane(table_menu);
		contentPane.add(js_menu, BorderLayout.WEST);
		
		table_step = new JTable(tableModel_step);
		js_step=new JScrollPane(table_step);
		contentPane.add(js_step, BorderLayout.CENTER);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		this.table_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=MenuUser.this.table_menu.getSelectedRow();
				if(i<0) {
					return;
				}
				MenuUser.this.loadstep(i);
			}
		});
		btnNewButton = new JButton("后退");
		btnNewButton.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		
		btnNewButton_1 = new JButton("搜索");
		btnNewButton_1.addActionListener((e)->{
			String name=JOptionPane.showInputDialog("请输入菜名");
			if(name.isEmpty()) {
				loadmenu();
			}
			else {
				loadmenu(name);
			}
		});
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton);
		loadmenu() ;
	}
	public void loadmenu() {
		try {
			list_menu=LoginStart.menuManager.loadallMenus();
			tbldate_menu=new Object[list_menu.size()][2];
			for(int i=0;i<list_menu.size();i++) {
				tbldate_menu[i][0]=list_menu.get(i).getId();
				tbldate_menu[i][1]=list_menu.get(i).getName();
			}
			tableModel_menu.setDataVector(tbldate_menu, titles_menu);
			this.table_menu.validate();
			this.table_menu.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadmenu(String string) {
		try {
			list_menu=LoginStart.menuManager.loadallMenus(string);
			tbldate_menu=new Object[list_menu.size()][2];
			for(int i=0;i<list_menu.size();i++) {
				tbldate_menu[i][0]=list_menu.get(i).getId();
				tbldate_menu[i][1]=list_menu.get(i).getName();
			}
			tableModel_menu.setDataVector(tbldate_menu, titles_menu);
			this.table_menu.validate();
			this.table_menu.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadstep(int menuidx) {
		if(menuidx<0) return ;
		System.out.println("menuidx "+menuidx);
		menu=list_menu.get(menuidx);
		try {
			list_step=LoginStart.menuManager.loadthisStep(menu);
			tbldate_step=new Object[list_step.size()][4];
			for(int i=0;i<list_step.size();i++) {
				tbldate_step[i][0]=list_step.get(i).getStep_id();
				tbldate_step[i][1]=list_step.get(i).getMenu_id();
				tbldate_step[i][2]=list_step.get(i).getProduct();
				tbldate_step[i][3]=list_step.get(i).getStatement();
			}
			tableModel_step.setDataVector(tbldate_step, titles_step);
			this.table_step.validate();
			this.table_step.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setfmain(FMain f) {
		this.fMain=f;
	}

}
