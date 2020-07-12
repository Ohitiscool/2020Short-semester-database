package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javassist.tools.framedump;
import start.LoginStart;
import ui.FMain;
import util.BaseException;

import javax.swing.JTable;

public class Choose_type extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object addtitles[]= {"类别id","类别名","状态"};
	private Object tblData[][];
	private FMain fMain=null;
	private JScrollPane jScrollPane;
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");
	private List<model.Type> list;
	private model.Type type;
	private boolean retval=false;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Choose_type(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 705, 588);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
			table = new JTable(tableModel);
			jScrollPane=new JScrollPane(table);
			contentPanel.add(jScrollPane, BorderLayout.CENTER);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
				listalltype();
				
		
	}
	public void listalltype() {//重载和加载页面
		try {
			list =LoginStart.typeManager.loadallTypes("In the sale");
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
			e.printStackTrace();
		}
	}
	
	
	public  boolean checkvail() {
		return getvalue();
		// TODO Auto-generated method stub
	}
	public boolean  getvalue() {
		if(list.size()!=0) {
			int i=this.table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择类型","提示",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			int n=Integer.parseInt(this.tblData[i][0].toString());
			try {
				type=LoginStart.typeManager.anyType(n);
				return true;
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "没有商品类别可选","提示",JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			return false;
		}
		return (type!=null);
	}
	
	public boolean exec() {
		return retval;
	}
	
	public model.Type gettype(){
		return type;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			if(checkvail()) {
				retval=true;
				this.setVisible(false);
			}
		}
		else if(e.getSource()==cancelButton) {
			this.setVisible(false);
		}
		// TODO Auto-generated method stub
	}
	

}
