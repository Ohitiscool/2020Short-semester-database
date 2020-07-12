package system.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Address;
import model.Type;
import start.LoginStart;
import ui.FMain;
import util.BaseException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class TypeUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object addtitles[]= {"类别id","类别名","状态"};
	private Object tblData[][];
	private JScrollPane jScrollPane;
	private SystemMain systemMain;
	private JButton btnNewButton_1 = new JButton("修改类别");
	private JButton btnNewButton = new JButton("添加类别");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypeUI frame = new TypeUI();
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
	public TypeUI() {
		setTitle("商品类别管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		

		toolBar.add(btnNewButton);
		
		toolBar.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		btnNewButton.addActionListener(this);
		JButton btnNewButton_back = new JButton("后退");
		btnNewButton_back.addActionListener((e)->{
			this.systemMain.setEnabled(true);
			this.setVisible(false);
		});
		toolBar.add(btnNewButton_back);
		
		table = new JTable(tableModel);
		jScrollPane=new JScrollPane(table);
		contentPane.add(jScrollPane, BorderLayout.CENTER);
		listalltype();
	}
	public void setFmain(SystemMain f) {
		this.systemMain=f;
	}
	public void listalltype() {//重载和加载页面
		try {
			List<model.Type> list =LoginStart.typeManager.loadallTypes();
			tblData=new Object[list.size()][3];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getType_id();
				tblData[i][1]=list.get(i).getType_name();
				tblData[i][2]=list.get(i).getState();
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
		if(e.getSource()==btnNewButton) {
			Addtype addtype=new Addtype(this, "添加类别", true);
			addtype.setVisible(true);
			if(addtype.exec()) 
			{
				listalltype();
			}
			
			System.out.println(addtype.exec()+" isokay?");

		}
		else if(e.getSource()==btnNewButton_1) { //修改列别
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择对象","提示",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int n=Integer.parseInt(this.tblData[i][0].toString());//n为
			model.Type type=new model.Type();
			type.setType_id(n);
			type.setType_name(this.tblData[i][1].toString());
			type.setState(this.tblData[i][2].toString());
			Modify_type modify_typ=new Modify_type(this, "修改类别", true, type);
			modify_typ.setVisible(true);
			if(modify_typ.exec()) {
				this.listalltype();
			}
			System.out.println(modify_typ.exec()+"dsadasdsadsadas");
		}
		// TODO Auto-generated method stub
	}

	

}
