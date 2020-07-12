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
public class Modify_type extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_name=new JTextField();
	private JComboBox<String> comboBox=new JComboBox<String>();
    private boolean retval=false;
    private model.Type types;
    private JButton okButton = new JButton("确定");
    private JButton cancelButton = new JButton("取消");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Modify_type(JFrame f,String s,boolean model,model.Type p) {
		super(f,s,model);
		this.types=p;
		setBounds(100, 100, 356, 343);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("类别名");
		lblNewLabel_1.setBounds(60, 81, 54, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("类别状态");
		lblNewLabel_2.setBounds(60, 145, 54, 15);
		contentPanel.add(lblNewLabel_2);
		
		okButton.addActionListener(this);
			okButton.setBounds(81, 211, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
	
		
			cancelButton.addActionListener((e)->{
				this.setVisible(false);
			});
			cancelButton.setBounds(173, 211, 69, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		
		
		textField_name.setBounds(137, 78, 104, 21);
		contentPanel.add(textField_name);
		textField_name.setColumns(10);
		
		comboBox.setBounds(137, 142, 104, 21);
		contentPanel.add(comboBox);
		comboBox.addItem("上架");
		comboBox.addItem("下架");
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		textField_name.setText(types.getType_name());
	
	}
	public String jud() {
		String state=(String)comboBox.getSelectedItem();
		if(state.equals("上架")) return "In the sale";
		else return "sold out";
	}
	public boolean checkvail() {
		getvalue();
		if(this.types.getType_name().isEmpty()) {
			JOptionPane.showMessageDialog(this, "类别名不得为空");
			return false;
		}
		return true;
	}
	public model.Type getvalue() {
		types.setType_name(textField_name.getText().trim());
		types.setState(jud());
		return types;
	}
	public boolean exec() {
		return retval;
	}
	public void modify() {
		try {
			LoginStart.typeManager.modify(types);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return ;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			if(checkvail()) {
				retval=true;
				modify();
				this.setVisible(false);
			}
		}
		// TODO Auto-generated method stub
		
	}
	
}
