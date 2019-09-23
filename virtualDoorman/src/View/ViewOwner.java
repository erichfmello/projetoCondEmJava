package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.AllPermission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controler.*;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewOwner {

	public JFrame frmOwner;
	private JTextField txtName;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtDdd;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JComboBox comboBoxAppartament;
	
	// variáveis
	protected String cpf;
	protected String name;
	protected String rg;
	protected String ddd;
	protected String phone;
	protected String email;
	protected String cnpj;
	protected int appartament;
	
	// Conexao
	protected ControlerOwner controlerOwner = new ControlerOwner();
	protected ControlerAppartmentOwner controlerAppartmentOwner;
	protected Connection conn;
	Statement comandoSql;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOwner window = new ViewOwner();
					window.frmOwner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewOwner() {
		initialize();
	}
	
	public ViewOwner(String cnpj) {
		initialize();
		this.cnpj = cnpj;
	}
	
	public ViewOwner(String cnpj, String name, String rg, String cpf, String ddd, String phone, String email) {
		this.cnpj = cnpj;
		this.cpf = cpf;
		this.name = name;
		this.rg = rg;
		this.ddd = ddd;
		this.phone = phone;
		this.email = email;
		
		initialize();
		
		txtName.setText(name);
		txtRg.setText(rg);
		txtCpf.setText(cpf);
		txtDdd.setText(ddd);
		txtPhone.setText(phone);
		txtEmail.setText(email);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOwner = new JFrame();
		frmOwner.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				controlerOwner.selectAppartments(cnpj);
					
				// comboBoxAppartament.setModel(new DefaultComboBoxModel(new String[] {"a", "b"}));
				
				DefaultComboBoxModel defaultListModelAppartaments = new DefaultComboBoxModel();
				defaultListModelAppartaments.addElement("apto");
				for(int i = 0; i < controlerOwner.getTotalOwner(); i++) {
					defaultListModelAppartaments.addElement(controlerOwner.getAppartents()[i]);
				}
				comboBoxAppartament.setModel(defaultListModelAppartaments);
			}
		});
		frmOwner.setTitle("Moradores");
		frmOwner.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\pessoas.png"));
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width / 2 - 255;
		int height = dimension.height / 2 - 205;
		frmOwner.setBounds(width, height, 420, 221);
		frmOwner.setResizable(false);
		frmOwner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOwner.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(10, 11, 60, 14);
		frmOwner.getContentPane().add(lblName);
		
		JLabel lblRg = new JLabel("R.G.:");
		lblRg.setBounds(10, 36, 60, 14);
		frmOwner.getContentPane().add(lblRg);
		
		JLabel lblPhone = new JLabel("e-mail:");
		lblPhone.setBounds(10, 86, 60, 14);
		frmOwner.getContentPane().add(lblPhone);
		
		JLabel lblEmail = new JLabel("Telefone:");
		lblEmail.setBounds(10, 61, 60, 14);
		frmOwner.getContentPane().add(lblEmail);
		
		JLabel lblCpf = new JLabel("C.P.F.:");
		lblCpf.setBounds(240, 36, 60, 14);
		frmOwner.getContentPane().add(lblCpf);
		
		txtName = new JTextField();
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				frmOwner.setTitle(txtName.getText());
			}
		});
		txtName.setBounds(90, 11, 320, 20);
		frmOwner.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtRg = new JTextField();
		txtRg.setColumns(10);
		txtRg.setBounds(90, 36, 130, 20);
		frmOwner.getContentPane().add(txtRg);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(320, 36, 90, 20);
		frmOwner.getContentPane().add(txtCpf);
		
		txtDdd = new JTextField();
		txtDdd.setColumns(10);
		txtDdd.setBounds(90, 61, 30, 20);
		frmOwner.getContentPane().add(txtDdd);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(130, 61, 90, 20);
		frmOwner.getContentPane().add(txtPhone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(90, 86, 320, 20);
		frmOwner.getContentPane().add(txtEmail);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cpf = txtCpf.getText();
				name = txtName.getText();
				
				if(txtRg.getText().isEmpty()) {
					controlerOwner = new ControlerOwner(cpf, name);
					
				} else {
					rg = txtRg.getText();
					ddd = txtDdd.getText();
					phone = txtPhone.getText();
					email = txtEmail.getText();
					
					controlerOwner = new ControlerOwner(cpf, name, rg, ddd, phone, email);
				}
				
				controlerOwner.insertOwner();
				
				appartament = Integer.parseInt(comboBoxAppartament.getSelectedItem().toString());
				controlerAppartmentOwner = new ControlerAppartmentOwner(appartament, cpf);
				controlerAppartmentOwner.insertAppartmentOwner();
				
				frmOwner.dispose();
				
			}
		});
		btnOk.setBounds(320, 126, 90, 25);
		frmOwner.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmOwner.dispose();
			}
		});
		btnCancel.setBounds(320, 161, 90, 25);
		frmOwner.getContentPane().add(btnCancel);
		
		JButton btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCpf.getText().isEmpty()) {
					cpf = txtCpf.getText();
					controlerOwner = new ControlerOwner();
					controlerOwner.deleteOwner(cpf);
					
					frmOwner.dispose();
				}
			}
		});
		btnDelete.setBounds(210, 162, 90, 25);
		frmOwner.getContentPane().add(btnDelete);
		
		JLabel lblAppartment = new JLabel("apto:");
		lblAppartment.setBounds(240, 61, 60, 14);
		frmOwner.getContentPane().add(lblAppartment);
		
		comboBoxAppartament = new JComboBox();
		comboBoxAppartament.setBounds(320, 61, 90, 20);
		frmOwner.getContentPane().add(comboBoxAppartament);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtCpf.getText().isEmpty()) {
					cpf = txtCpf.getText();
					name = txtName.getText();
					rg = txtRg.getText();
					ddd = txtDdd.getText();
					phone = txtPhone.getText();
					email = txtEmail.getText();
					
					appartament = Integer.parseInt(comboBoxAppartament.getSelectedItem().toString());
					
					controlerOwner.updateOwner(cpf, name, rg, ddd, phone, appartament, email);
					
					frmOwner.dispose();
				}
			}
		});
		btnAtualizar.setBounds(210, 127, 90, 25);
		frmOwner.getContentPane().add(btnAtualizar);
	}
}
