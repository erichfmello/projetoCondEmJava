package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import Model.ModelResidentialData;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import Controler.*;
import java.sql.*;

public class ViewResidentialData {

	protected JFrame frmResidentialData;

	private JLabel lblAddress;
	private JLabel lblNeighborhood;
	private JLabel lblCep;
	private JLabel lblUf;
	private JLabel lblDivisoDosApartamentos;
	private JLabel lblDadosDaLocalizao;
	private JLabel lblFloor;
	private JLabel lblCobertura;
	private JLabel lblApartmentLastFlorr;
	private JLabel lblApartmentPerFloor;
	private JLabel lblBlock;
	private JLabel lblTotalApartment;
	private JLabel lblTotalApartmentNumber;
	
	private JTextField txtName;
	private JTextField txtNumber;
	private JTextField txtAddress;
	private JTextField txtNeighborhood;
	private JTextField txtCep;
	private JTextField txtCnpj;
	private JTextField txtFloor;
	private JTextField txtApartmentLastFloor;
	private JTextField txtApartmentPerFloor;
	private JTextField txtBlock;
	
	JButton btnNewButton;

	JComboBox comboBoxLastFloor;
	JComboBox comboBoxUf; 
		
	// Variáveis
	protected String name, address, cep, neighborhood, uf, cnpj;
	protected int number, appartmentLastFloor, floor, appartmentPerFloor, Block;
	
	private String userName;
	private String userPassword;
	
	// Conexao
	protected ControlerResidentialData controlerResidentialData = new ControlerResidentialData();
	protected ControlerAppartment controlerAppartment;
	private ControlerLogin controlerLogin;
	protected Connection conn;
	Statement comandoSQL;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewResidentialData window = new ViewResidentialData();
					window.frmResidentialData.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewResidentialData() {
		initialize();
	}
	
	public ViewResidentialData(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
		initialize();
	}
	
  	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResidentialData = new JFrame();
		frmResidentialData.getContentPane().setBackground(Color.WHITE);
		frmResidentialData.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\CadastroClientes\\ProjetoCondominio\\img\\principal.jpg"));
		frmResidentialData.setTitle("Dados residenci\u00E1is");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width / 2 - 255;
		int height = dimension.height / 2 - 205;
		frmResidentialData.setBounds(width, height, 510, 411);
		frmResidentialData.setResizable(false);
		frmResidentialData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResidentialData.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(10, 36, 65, 14);
		frmResidentialData.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(85, 36, 265, 20);
		frmResidentialData.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNumber = new JLabel("N\u00FAmero:");
		lblNumber.setBounds(370, 36, 50, 14);
		frmResidentialData.getContentPane().add(lblNumber);
		
		txtNumber = new JTextField();
		txtNumber.setColumns(10);
		txtNumber.setBounds(430, 36, 70, 20);
		frmResidentialData.getContentPane().add(txtNumber);
		
		lblAddress = new JLabel("Endere\u00E7o:");
		lblAddress.setBounds(10, 61, 65, 14);
		frmResidentialData.getContentPane().add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(85, 61, 265, 20);
		frmResidentialData.getContentPane().add(txtAddress);
		
		lblNeighborhood = new JLabel("Bairro:");
		lblNeighborhood.setBounds(10, 86, 65, 14);
		frmResidentialData.getContentPane().add(lblNeighborhood);
		
		txtNeighborhood = new JTextField();
		txtNeighborhood.setColumns(10);
		txtNeighborhood.setBounds(85, 86, 265, 20);
		frmResidentialData.getContentPane().add(txtNeighborhood);
		
		lblCep = new JLabel("C.E.P.:");
		lblCep.setBounds(370, 61, 50, 14);
		frmResidentialData.getContentPane().add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(430, 61, 70, 20);
		frmResidentialData.getContentPane().add(txtCep);
		
		lblUf = new JLabel("U.F.:");
		lblUf.setBounds(370, 86, 50, 14);
		frmResidentialData.getContentPane().add(lblUf);
		
		comboBoxUf = new JComboBox();
		comboBoxUf.setModel(new DefaultComboBoxModel(new String[] {"U.F.", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", 
				"SE", "SP", "TO"}));
		comboBoxUf.setBounds(430, 86, 70, 20);
		frmResidentialData.getContentPane().add(comboBoxUf);
		
		JLabel lblCnpj = new JLabel("C.N.P.J.:");
		lblCnpj.setBounds(10, 111, 65, 14);
		frmResidentialData.getContentPane().add(lblCnpj);
		
		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(85, 111, 415, 20);
		frmResidentialData.getContentPane().add(txtCnpj);
		
		lblDivisoDosApartamentos = new JLabel("Divis\u00E3o dos apartamentos");
		lblDivisoDosApartamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDivisoDosApartamentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDivisoDosApartamentos.setBounds(10, 161, 490, 25);
		frmResidentialData.getContentPane().add(lblDivisoDosApartamentos);
		
		lblDadosDaLocalizao = new JLabel("Dados da localiza\u00E7\u00E3o");
		lblDadosDaLocalizao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosDaLocalizao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDadosDaLocalizao.setBounds(10, 11, 490, 25);
		frmResidentialData.getContentPane().add(lblDadosDaLocalizao);
		
		lblFloor = new JLabel("Andares:");
		lblFloor.setBounds(10, 211, 60, 14);
		frmResidentialData.getContentPane().add(lblFloor);
		
		lblCobertura = new JLabel("Cobertura:");
		lblCobertura.setBounds(10, 186, 60, 14);
		frmResidentialData.getContentPane().add(lblCobertura);
		
		lblApartmentLastFlorr = new JLabel("Apartamentos na cobertura:");
		lblApartmentLastFlorr.setBounds(175, 186, 170, 14);
		lblApartmentLastFlorr.setVisible(false);
		frmResidentialData.getContentPane().add(lblApartmentLastFlorr);
		
		txtApartmentLastFloor = new JTextField();
		txtApartmentLastFloor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					double total;
					if(txtApartmentLastFloor.isVisible()) {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText()) + Double.parseDouble(txtApartmentLastFloor.getText());
					} else {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText());
					}
					
					lblTotalApartmentNumber.setText(String.valueOf(total));	
					lblTotalApartment.setVisible(true);
					lblTotalApartmentNumber.setVisible(true);
				} catch (NumberFormatException exception) {
				}
			}
		});
		txtApartmentLastFloor.setColumns(10);
		txtApartmentLastFloor.setBounds(365, 183, 70, 20);
		txtApartmentLastFloor.setVisible(false);
		frmResidentialData.getContentPane().add(txtApartmentLastFloor);
		
		comboBoxLastFloor = new JComboBox();
		comboBoxLastFloor.setModel(new DefaultComboBoxModel(new String[] {"S/N", "Sim", "Não"}));
		comboBoxLastFloor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(comboBoxLastFloor.getSelectedItem() == "Sim") {
					lblApartmentLastFlorr.setVisible(true);
					txtApartmentLastFloor.setVisible(true);
				} else {
					lblApartmentLastFlorr.setVisible(false);
					txtApartmentLastFloor.setVisible(false);
					txtApartmentLastFloor.setText(null);;
				}
			}
		});
		comboBoxLastFloor.setBounds(85, 183, 70, 20);
		frmResidentialData.getContentPane().add(comboBoxLastFloor);
		
		lblTotalApartmentNumber = new JLabel("");
		lblTotalApartmentNumber.setVisible(false);
		lblTotalApartmentNumber.setBounds(310, 261, 40, 14);
		frmResidentialData.getContentPane().add(lblTotalApartmentNumber);
		
		lblApartmentPerFloor = new JLabel("Apartamentos por andar:");
		lblApartmentPerFloor.setBounds(175, 211, 170, 14);
		frmResidentialData.getContentPane().add(lblApartmentPerFloor);
		
		txtApartmentPerFloor = new JTextField();
		txtApartmentPerFloor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					double total;
					if(txtApartmentLastFloor.isVisible()) {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText()) + Double.parseDouble(txtApartmentLastFloor.getText());
					} else {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText());
					}
					
					lblTotalApartmentNumber.setText(String.valueOf(total));	
					lblTotalApartment.setVisible(true);
					lblTotalApartmentNumber.setVisible(true);
				} catch (NumberFormatException exception) {
				}
			}
		});
		txtApartmentPerFloor.setColumns(10);
		txtApartmentPerFloor.setBounds(365, 211, 70, 20);
		frmResidentialData.getContentPane().add(txtApartmentPerFloor);
		
		lblBlock = new JLabel("Blocos:");
		lblBlock.setBounds(10, 236, 50, 14);
		frmResidentialData.getContentPane().add(lblBlock);
		
		txtBlock = new JTextField();
		txtBlock.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					double total;
					if(txtApartmentLastFloor.isVisible()) {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText()) + Double.parseDouble(txtApartmentLastFloor.getText());
					} else {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText());
					}
					
					lblTotalApartmentNumber.setText(String.valueOf(total));	
					lblTotalApartment.setVisible(true);
					lblTotalApartmentNumber.setVisible(true);
				} catch (NumberFormatException exception) {
				}
			}
		});
		txtBlock.setColumns(10);
		txtBlock.setBounds(85, 236, 70, 20);
		frmResidentialData.getContentPane().add(txtBlock);
		
		lblTotalApartment = new JLabel("Total de apartamentos:");
		lblTotalApartment.setVisible(false);
		lblTotalApartment.setBounds(155, 261, 140, 14);
		frmResidentialData.getContentPane().add(lblTotalApartment);
		
		txtFloor = new JTextField();
		txtFloor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					double total;
					if(txtApartmentLastFloor.isVisible()) {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText()) + Double.parseDouble(txtApartmentLastFloor.getText());
					} else {
						total = Double.parseDouble(txtFloor.getText()) * Double.parseDouble(txtApartmentPerFloor.getText()) * Double.parseDouble(txtBlock.getText());
					}
					
					lblTotalApartmentNumber.setText(String.valueOf(total));	
					lblTotalApartment.setVisible(true);
					lblTotalApartmentNumber.setVisible(true);
				} catch (NumberFormatException exception) {
				}
			}
		});
		txtFloor.setColumns(10);
		txtFloor.setBounds(85, 211, 70, 20);
		frmResidentialData.getContentPane().add(txtFloor);
		
		btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Adicionando valores nas variáveis
				name = txtName.getText();
				address = txtAddress.getText();
				cep = txtCep.getText();
				neighborhood = txtNeighborhood.getText();
				uf = comboBoxUf.getSelectedItem().toString();					
				cnpj = txtCnpj.getText();
				
				if(name.isEmpty() || address.isEmpty() || cep.isEmpty() || neighborhood.isEmpty() || uf.isEmpty() || cnpj.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta preenchimento dos campo");
					if(name.isEmpty()) {
						lblName.setForeground(Color.red);
					}
					if(address.isEmpty()) {
						lblAddress.setForeground(Color.red);
					} 
					if(cep.isEmpty()) {
						lblCep.setForeground(Color.red);
					} 
					if(neighborhood.isEmpty()) {
						lblNeighborhood.setForeground(Color.red);
					} 
					if(uf.isEmpty()) {
						lblUf.setForeground(Color.red);
					} 
					if (cnpj.isEmpty()) {
						lblCnpj.setForeground(Color.red);
					}
				}
				
				if(comboBoxLastFloor.getSelectedItem() == "Sim") {
					appartmentLastFloor = Integer.parseInt(txtApartmentLastFloor.getText());
				} else {
					appartmentLastFloor = 0;
				}					
				
				try {
					number = Integer.parseInt(txtNumber.getText());
					floor = Integer.parseInt(txtFloor.getText());
					appartmentPerFloor = Integer.parseInt(txtApartmentPerFloor.getText());				
					Block = Integer.parseInt(txtBlock.getText());
					
					controlerResidentialData = new ControlerResidentialData(name, address, cep, neighborhood, 
							uf, cnpj, number, appartmentLastFloor, floor, appartmentPerFloor, Block);
					
					controlerResidentialData.insertResidentialData();
					
					for(int k = 0; k < Block; k++) {
						int controler = 0;
						for(int i = 0; i < floor; i++) {
							for (int j = 0; j < appartmentPerFloor; j++) {
								/* 
								 * private int appartment;
								 * private int floor;
								 * private int finalNumber;
								 * private String block;
								 */
								
								int appartment = Integer.parseInt(String.valueOf(i+1) + String.valueOf(j+1)); 
								int floor = i+1;
								int finalNumber = j+1;
								String block = String.valueOf(k+1);
								
								controlerAppartment = new ControlerAppartment(appartment, floor, finalNumber, block);
								controlerAppartment.insertAppartment(cnpj);
								
							}
							controler++;
						}
						
						for(int i = 0; i < appartmentLastFloor; i++) {
							int appartment = Integer.parseInt(String.valueOf(controler + 1) + String.valueOf(i+1)); 
							int floor = controler+1;
							int finalNumber = i+1;
							String block = String.valueOf(k+1);
							
							controlerAppartment = new ControlerAppartment(appartment, floor, finalNumber, block);
							controlerAppartment.insertAppartment(cnpj);
						}
					}
					
					controlerLogin = new ControlerLogin();
					controlerLogin.insertNewUser(userName, userPassword, cnpj);
					
					frmResidentialData.dispose();					

				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Valores inválidos");
					lblNumber.setForeground(Color.red);
					lblFloor.setForeground(Color.red);
					lblApartmentPerFloor.setForeground(Color.red);
					lblBlock.setForeground(Color.red);
				}
				
			}
		});
		btnNewButton.setBounds(410, 311, 90, 25);
		frmResidentialData.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				frmResidentialData.dispose();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmResidentialData.dispose();
			}
		});
		btnCancelar.setBounds(410, 346, 90, 25);
		frmResidentialData.getContentPane().add(btnCancelar);
	}
}
