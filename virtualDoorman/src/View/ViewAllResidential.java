package View;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JScrollPane;

import Controler.ControlerLogin;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewAllResidential {

	protected JFrame frmAllResidential;
	private JList listAllResidential;
	private JScrollPane scrollPane;
	
	// Variaveis comuns
	private String name;
	private String password;
	private String cnpj;
	private int i = 0;
	
	// Variaveis View
	ViewLogin viewLogin;
	ViewResidentialData viewResidentialData;
	ViewHome viewHome;
	
	// Variaveis controler
	ControlerLogin controlerLogin = new ControlerLogin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllResidential window = new ViewAllResidential();
					window.frmAllResidential.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAllResidential() {
		initialize();
	}
	
	public ViewAllResidential(ControlerLogin controlerLogin, String name, String password) {
		this.controlerLogin = controlerLogin;
		this.name = name;
		this.password = password;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAllResidential = new JFrame();
		frmAllResidential.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				seedListAllResidential();
			}
		});
		frmAllResidential.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\principal.jpg"));
		frmAllResidential.setTitle("Condom\u00EDnios");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 450/2;
		int height = dimension.height/2 - 500/2;
		frmAllResidential.setBounds(width, height, 450, 500);
		frmAllResidential.setResizable(false);
		frmAllResidential.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAllResidential.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 315, 439);
		frmAllResidential.getContentPane().add(scrollPane);
		
		listAllResidential = new JList();
		scrollPane.setViewportView(listAllResidential);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrincipalProgram();
			}
		});
		btnOk.setBounds(335, 11, 90, 25);
		frmAllResidential.getContentPane().add(btnOk);
		
		JButton btnNew = new JButton("Novo");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criateNewResidential();
			}
		});
		btnNew.setBounds(335, 47, 90, 25);
		frmAllResidential.getContentPane().add(btnNew);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnLogin();
			}
		});
		btnCancel.setBounds(335, 425, 90, 25);
		frmAllResidential.getContentPane().add(btnCancel);
	}
	
	// Modularização
	private void returnLogin() {
		viewLogin = new ViewLogin();
		frmAllResidential.setVisible(false);
		viewLogin.frmLogin.setVisible(true);
	}
	
	private void seedListAllResidential() {
		controlerLogin.selectResidentialNames(name, password);
		
		DefaultListModel defaultListModelAllResidential = new DefaultListModel();
		for(int i = 0; i < controlerLogin.getTotalResidential(); i++) {
			defaultListModelAllResidential.add(i, controlerLogin.getModelAllResidential()[i].getName() + " - cnpj: " + controlerLogin.getModelAllResidential()[i].getCnpj());
		}
		listAllResidential.setModel(defaultListModelAllResidential);
	}
	
	private void criateNewResidential() {
		viewResidentialData = new ViewResidentialData(name, password);
		viewResidentialData.frmResidentialData.setVisible(true);
	}
	
	private void openPrincipalProgram() {
		i = listAllResidential.getSelectedIndex();
		
		cnpj = controlerLogin.getModelAllResidential()[i].getCnpj();
		
		frmAllResidential.setVisible(false);
		
		viewHome = new ViewHome(cnpj);
		viewHome.frmVirtualDoorman.setVisible(true);
	}
}
