package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Menu;
import model.Menu_step;
import start.LoginStart;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStep extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_food;
	private JTextField textField_statement;
	private Menu menu;
	private Menu_step menu_step;
	private boolean retval=false;
	

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public AddStep(JFrame f,String title,boolean b,Menu m) {
		super(f,title,b);
		setBounds(100, 100, 387, 517);
		this.menu=m;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("食材");
		lblNewLabel.setBounds(26, 37, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("操作");
		lblNewLabel_1.setBounds(26, 132, 54, 15);
		contentPanel.add(lblNewLabel_1);
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener((e)->{
				if(getvalue()) {
					menu_step.setMenu_id(menu.getId());
					add();
					retval=true;
					this.setVisible(false);
				}
			});
			okButton.setBounds(107, 394, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(190, 394, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		textField_food = new JTextField();
		textField_food.setBounds(57, 62, 232, 60);
		contentPanel.add(textField_food);
		textField_food.setColumns(10);
		
		textField_statement = new JTextField();
		textField_statement.setBounds(59, 157, 232, 192);
		contentPanel.add(textField_statement);
		textField_statement.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	private boolean getvalue() {
		String food=textField_food.getText();
		String statemen=textField_statement.getText();
		if(food.isEmpty()) {
			JOptionPane.showMessageDialog(null, "食材字段不得为空", "错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(statemen.isEmpty()) {
			JOptionPane.showMessageDialog(null, "操作字段不得为空","错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		menu_step=new Menu_step();
		menu_step.setProduct(food);
		menu_step.setStatement(statemen);
		return true;
	}
	public boolean exec() {
		return retval;
	}
	private void add() {
		try {
			LoginStart.menuManager.addstep(this.menu_step);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
