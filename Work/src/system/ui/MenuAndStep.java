package system.ui;

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

import javassist.compiler.ast.NewExpr;
import model.Menu;
import model.Menu_step;
import start.LoginStart;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAndStep extends JFrame {

	private JPanel contentPane;
	private JTable table_Menu;
	private JTable table_step;
	DefaultTableModel tableModel_menu=new DefaultTableModel();
	DefaultTableModel tableModel_step=new DefaultTableModel();
	private Object titles_menu[]= {"菜谱号","菜谱名"};
	private Object titles_step[]= {"步骤号","菜谱号","食材","操作"};
	private Object tbldate_menu[][];
	private Object tbldate_step[][];
	private SystemMain systemMain;
	private JScrollPane js_menu;
	private JScrollPane js_step;
	private List<Menu> list_menu;
	private List<Menu_step> list_step;
	private Menu menu;
	private SystemMain fMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAndStep frame = new MenuAndStep();
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
	public MenuAndStep() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton button = new JButton("添加菜单");
		button.addActionListener((e)->{
			String name=JOptionPane.showInputDialog(this,"请输入菜单名字");
			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(null,"菜名不得为空","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				LoginStart.menuManager.addmenu(name);
				loadmenu();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		toolBar.add(button);
		
		JButton button_1 = new JButton("添加步骤");
		button_1.addActionListener((e)->{
			int i=MenuAndStep.this.table_Menu.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择菜谱","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			AddStep addStep=new AddStep(this,"步骤信息",true,menu);
			addStep.setVisible(true);
			if(addStep.exec()) {
				loadstep(i);
			}
		});
		toolBar.add(button_1);
		
		JButton button_2 = new JButton("删除菜单");
		button_2.addActionListener((e)->{
			int i=table_Menu.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择菜谱", "错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			try {
				LoginStart.menuManager.deletemenu(menu);
				loadmenu();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		toolBar.add(button_2);
		
		JButton button_3 = new JButton("删除步骤");
		button_3.addActionListener((e)->{
			int i2=table_Menu.getSelectedRow();
			int i=table_step.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(tbldate_step[i][0].toString());
			try {
				LoginStart.menuManager.deletestep(n);
				loadstep(i2);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		});
		toolBar.add(button_3);
		
		JButton btnNewButton = new JButton("后退");
		btnNewButton.addActionListener((e)->{
			this.setVisible(false);
			fMain.setEnabled(true);
		});
		
		JButton btnNewButton_1 = new JButton("搜索");
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
		
		table_Menu = new JTable(tableModel_menu);
		js_menu=new JScrollPane(table_Menu);
		contentPane.add(js_menu, BorderLayout.WEST);
		loadmenu();
		
		
		table_step = new JTable(tableModel_step);
		js_step=new JScrollPane(table_step);
		contentPane.add(js_step, BorderLayout.CENTER);
		

		this.table_Menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=MenuAndStep.this.table_Menu.getSelectedRow();
				if(i<0) {
					return;
				}
				MenuAndStep.this.loadstep(i);
			}
		});
		
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
			this.table_Menu.validate();
			this.table_Menu.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadmenu(String name) {
		try {
			list_menu=LoginStart.menuManager.loadallMenus(name);
			tbldate_menu=new Object[list_menu.size()][2];
			for(int i=0;i<list_menu.size();i++) {
				tbldate_menu[i][0]=list_menu.get(i).getId();
				tbldate_menu[i][1]=list_menu.get(i).getName();
			}
			tableModel_menu.setDataVector(tbldate_menu, titles_menu);
			this.table_Menu.validate();
			this.table_Menu.repaint();
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
	public void setfmain(SystemMain f) {
		this.fMain=f;
	}
	
	

}
