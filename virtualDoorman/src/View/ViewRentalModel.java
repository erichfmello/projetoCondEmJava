package View;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controler.*;

public class ViewRentalModel {

	public JFrame frmRentalModel;
	private JTextField txtPrice;
	private JTextPane txtDescription;
	private JLabel lblValor;
	
	// Variáveis comuns
	private String cnpj;
	private String description;
	private double price;
	
	// Variaveis do controler
	ControlerRentalModel controlerRentalModel = new ControlerRentalModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRentalModel window = new ViewRentalModel();
					window.frmRentalModel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewRentalModel() {
		initialize();
	}
	
	public ViewRentalModel(String cnpj) {
		this.cnpj = cnpj;
		initialize();
	}
	
	public ViewRentalModel(String cnpj, String description, double price) {
		this.cnpj = cnpj;
		this.description = description;
		this.price = price;
		initialize();
		
		txtDescription.setText(description);
		txtPrice.setText(String.valueOf(price));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentalModel = new JFrame();
		frmRentalModel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		frmRentalModel.setTitle("Modelo de aluguel");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 220;
		int height = dimension.height/2 - 150;
		frmRentalModel.setBounds(width, height, 440, 300);
		frmRentalModel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentalModel.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o do modelo de aluguel:");
		lblNewLabel.setBounds(10, 11, 243, 14);
		frmRentalModel.getContentPane().add(lblNewLabel);
		
		txtDescription = new JTextPane();
		txtDescription.setBounds(10, 36, 400, 150);
		frmRentalModel.getContentPane().add(txtDescription);
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 197, 50, 14);
		frmRentalModel.getContentPane().add(lblValor);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(65, 194, 86, 20);
		frmRentalModel.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean comparation = false;
				
				description = txtDescription.getText();
				price = Double.parseDouble(txtPrice.getText());
				
				controlerRentalModel = new ControlerRentalModel(cnpj, description, price);
				
				comparation = controlerRentalModel.selectRentalModel(cnpj);
				
				if(comparation == true) {
					controlerRentalModel = new ControlerRentalModel(cnpj, description, price);
					controlerRentalModel.updateRentalModel();
					
				} else {
					controlerRentalModel.insertRentalModel();					
				}
				
				frmRentalModel.dispose();
			}
		});
		btnOk.setBounds(321, 193, 89, 23);
		frmRentalModel.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentalModel.dispose();
			}
		});
		btnCancel.setBounds(321, 227, 89, 23);
		frmRentalModel.getContentPane().add(btnCancel);
	}
}
