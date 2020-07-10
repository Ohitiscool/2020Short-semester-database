package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;
import org.omg.PortableServer.AdapterActivator;
import org.omg.PortableServer.POA;

import model.Address;
import start.LoginStart;
import util.BaseException;

import javax.swing.JTable;

public class Chosse_Address extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DefaultTableModel tableModel=new DefaultTableModel();
	private Object[] titles= {"地址编号","省","城市","地区","联系人","联系号码"};
	private Object tblData[][];
	private Address address=null;
	private boolean retvale=false;
	JButton okButton = new JButton("确定");
	JButton cancelButton = new JButton("取消");
	List<Address> list;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public Chosse_Address(Frame f,String title,boolean model) {
		super(f,title,model);
		setTitle(title);
		setBounds(100, 100, 541, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(tableModel);
		JScrollPane jScrollPane=new JScrollPane(table);
		contentPanel.add(jScrollPane, BorderLayout.CENTER);
	
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			
			
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(this);
		listalladdress();
		}

	public void listalladdress() {
		try {
			 list =LoginStart.userManager.loadAddresses();
			 System.out.println(list.size()+"地址数量");
			tblData=new Object[list.size()][6];
			for(int i=0;i<list.size();i++) {
				tblData[i][0]=list.get(i).getAddress_id();
				tblData[i][1]=list.get(i).getAddress_province();
				tblData[i][2]=list.get(i).getAddress_city();
				tblData[i][3]=list.get(i).getAddress_area();
				tblData[i][4]=list.get(i).getAddress_person();
				tblData[i][5]=list.get(i).getAddress_tel();
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
	private boolean  getvalue() {
		if(list.size()!=0) {
			System.out.println("地址数量"+list.size());
			int i=this.table.getSelectedRow();
		    if(i<0) {
		    	JOptionPane.showMessageDialog(null, "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return false;
		    	}
		    int n=Integer.parseInt(this.tblData[i][0].toString());
		    System.out.println("地址id   "+n);
		    try {
		    	address=LoginStart.userManager.anyAddress(n);
		    	return true;
		    	} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    	}
			}
		else {
			JOptionPane.showMessageDialog(null, "此用户未设置地址","提示",JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			return false;
		}
		return (address!=null);
	}
		// TODO Auto-generated method stub
	public boolean checkvaild() {
		return getvalue();
	}
	public boolean exec() {
		return retvale;
	}
	public Address getaddress() {
		return address;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			if(checkvaild()) {
				retvale=true;
				this.setVisible(false);
			}
			
		}
		else if(e.getSource()==cancelButton) {

			this.setVisible(false);
		}
		// TODO Auto-generated method stub
	
	}



}
