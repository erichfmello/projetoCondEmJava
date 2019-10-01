package View;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.tools.Tool;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import Controler.*;

public class ViewAddGuest {

	public JFrame frmAddGuest;
	private JTextField txtName;
	private JTextField txtDocument;
	
	// Variaveis comuns
	private String name;
	private String document;
	private Date reservationDate;
	
	// Variaveis do controler
	ControlerAllGuest controlerAllGuest;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAddGuest window = new ViewAddGuest();
					window.frmAddGuest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAddGuest() {
		initialize();
	}
	
	public ViewAddGuest(Date reservationDate) {
		this.reservationDate = reservationDate;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddGuest = new JFrame();
		frmAddGuest.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		frmAddGuest.setTitle("Adicionar convidado");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 450/2;
		int height = dimension.height/2 - 180/2;
		frmAddGuest.setBounds(width, height, 450, 179);
		frmAddGuest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddGuest.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 80, 14);
		frmAddGuest.getContentPane().add(lblNome);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(10, 40, 80, 14);
		frmAddGuest.getContentPane().add(lblDocumento);
		
		txtName = new JTextField();
		txtName.setBounds(100, 8, 324, 20);
		frmAddGuest.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtDocument = new JTextField();
		txtDocument.setColumns(10);
		txtDocument.setBounds(100, 37, 324, 20);
		frmAddGuest.getContentPane().add(txtDocument);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = txtName.getText();
				document = txtDocument.getText();
				
				controlerAllGuest = new ControlerAllGuest(document, name, reservationDate);
				controlerAllGuest.insertNewGuest();
				
				frmAddGuest.dispose();
			}
		});
		btnOk.setBounds(334, 68, 90, 25);
		frmAddGuest.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddGuest.dispose();
			}
		});
		btnCancel.setBounds(334, 104, 90, 25);
		frmAddGuest.getContentPane().add(btnCancel);
	}
}
