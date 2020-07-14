package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Product;
import start.LoginStart;
import util.BaseException;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Choose_Product extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton okButton = new JButton("确定");
	private JButton cancelButton = new JButton("后退");
	private Product product;
	DefaultTableModel tabkleModel=new DefaultTableModel();
	private Object titles[]= {"商品id","类别Id","商品名称","价格","会员价","库存","规格","状态"};
	private Object tbldata[][];
	private List<Product> list;
	private JScrollPane jScrollPane;
	private boolean retval=false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Choose_Product(JFrame f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 622, 570);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable(tabkleModel);
			jScrollPane=new JScrollPane(table);
			contentPanel.add(jScrollPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.addActionListener((e)->{
					if(checkvail()) {
						retval=true;
						
						this.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton.addActionListener((e)->{
					this.setVisible(false);
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadproduct();
	}
	public void loadproduct() {
		try {
			list=LoginStart.productManager.loadall();
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
			tabkleModel.setDataVector(tbldata, titles);
			this.table.validate();
			this.table.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean checkvail() {
		return getvalue();
	}
	public boolean getvalue() {
		if(list.size()!=0) {
			int i=this.table.getSelectedRow();
			System.out.print("tablesize  "+list.size());
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			int n=Integer.parseInt(this.tbldata[i][0].toString());
		
				try {
					product=LoginStart.productManager.anyProduct(n);
				} catch (BaseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else {
			JOptionPane.showMessageDialog(null, "没有商品可选","提示",JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			return false;
		}
		return (product!=null);
	}
	public boolean exec() {
		return retval;
	}
	public Product getProduct() {
		return product;
	}

}
