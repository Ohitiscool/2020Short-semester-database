package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import model.*;
import start.LoginStart;
import util.BaseException;
public class Addtype extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_name=new JTextField();
	private JComboBox<String> comboBox=new JComboBox<String>();
    private boolean retval=false;
    private model.Type type=new model.Type();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Addtype(JFrame f,String s,boolean model) {
		super(f,s,model);
		setBounds(100, 100, 368, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("类别名");
		lblNewLabel_1.setBounds(60, 120, 54, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("类别状态");
		lblNewLabel_2.setBounds(60, 180, 54, 15);
		contentPanel.add(lblNewLabel_2);
		
		JButton okButton = new JButton("确定");
		okButton.addActionListener((e)->{
			if(checkvail()) {
				retval=true;
				add();
				setVisible(false);
				
			}
			
		});
			okButton.setBounds(88, 256, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
	
		
			JButton cancelButton = new JButton("取消");
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(186, 256, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		
		
	
		
		textField_name.setBounds(137, 117, 104, 21);
		contentPanel.add(textField_name);
		textField_name.setColumns(10);
		
		comboBox.setBounds(137, 177, 104, 21);
		contentPanel.add(comboBox);
		comboBox.addItem("上架");
		comboBox.addItem("下架");
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
	}
	public String jud() {
		String state=(String)comboBox.getSelectedItem();
		if(state.equals("上架")) return "In the sale";
		else return "sold out";
	}
	public boolean checkvail() {
		getvalue();
		if(type.getType_name().isEmpty()) {
			JOptionPane.showMessageDialog(this, "类别名不得为空");
			return false;
		}
		return true;
	}
	public void getvalue() {
		type.setType_name(textField_name.getText().trim());
		type.setState(jud());
	}
	public boolean exec() {
		return retval;
	}
	public void add() {
		try {
			LoginStart.typeManager.addTypes(type);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return ;
		}
	}
	
}
