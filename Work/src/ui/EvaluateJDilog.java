package ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jboss.logging.Message;

import javassist.tools.framedump;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EvaluateJDilog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBox=new JComboBox<String>();
	private JLabel lblNewLabel = new JLabel("星级");
	private JLabel lblNewLabel_1 = new JLabel("评语");
	private JTextPane textPane = new JTextPane();
	private int product_id=0;
	private int order_id=0;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public EvaluateJDilog(Frame f,String s,boolean model,int product_id,int order_id) {
		super(f,s,model);
		this.product_id=product_id;
		this.order_id=order_id;
		setBounds(100, 100, 516, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel.setBounds(86, 78, 54, 15);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1.setBounds(86, 156, 54, 15);
		contentPanel.add(lblNewLabel_1);
		
	
		comboBox.setBounds(140, 75, 65, 21);
		contentPanel.add(comboBox);
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		
		textPane.setBounds(140, 156, 236, 21);
		contentPanel.add(textPane);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("完成评价");
				okButton.addActionListener((e)->{
					String evaluation=textPane.getText();
					int level=jud();
					try {
						LoginStart.orderManager.evaluate_Order(order_id,evaluation, product_id, level);
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, ""+e1.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
						this.setVisible(false);
						e1.printStackTrace();
						return;
					}
					this.setVisible(false);
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener((e)->{
					this.setVisible(false);
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
	}
	public int jud() {
		String level=(String)comboBox.getSelectedItem();
		if("1".equals(level)) return 1;
		else if("2".equals(level)) return 2;
		else if("3".equals(level)) return 3;
		else if("4".equals(level)) return 4;
		else if("5".equals(level)) return 5;
		else return 5;
	}
}
