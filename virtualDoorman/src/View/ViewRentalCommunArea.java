package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import Controler.ControlerRentalModel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewRentalCommunArea {

	public JFrame frmRentalCommunAreas;
	
	// Variaveis comuns
	private String cnpj;
	
	// Variaveis de View
	ViewRentalModel viewRenalModel;
	
	// Variaveis do controler
	ControlerRentalModel controRentalModel = new ControlerRentalModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRentalCommunArea window = new ViewRentalCommunArea();
					window.frmRentalCommunAreas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewRentalCommunArea() {
		this.cnpj = "xxxxx";
		initialize();
	}
	
	public ViewRentalCommunArea(String cnpj) {
		this.cnpj = cnpj;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentalCommunAreas = new JFrame();
		frmRentalCommunAreas.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		frmRentalCommunAreas.setTitle("Aluguel de areas");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 139;
		int height = dimension.height/2 - 150;
		frmRentalCommunAreas.setBounds(width, height, 278, 300);
		frmRentalCommunAreas.setResizable(false);
		frmRentalCommunAreas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentalCommunAreas.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 138, 239);
		frmRentalCommunAreas.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNew = new JButton("Novo");
		btnNew.setBounds(158, 9, 90, 25);
		frmRentalCommunAreas.getContentPane().add(btnNew);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(158, 45, 90, 25);
		frmRentalCommunAreas.getContentPane().add(btnApagar);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmRentalCommunAreas.dispose();
			}
		});
		btnOk.setBounds(158, 81, 90, 25);
		frmRentalCommunAreas.getContentPane().add(btnOk);
		
		JButton btnModeloDeAlugual = new JButton("Modelo");
		btnModeloDeAlugual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verification = false;
				verification = controRentalModel.selectRentalModel(cnpj);
				
				if(verification == true) {
					viewRenalModel = new ViewRentalModel(cnpj, controRentalModel.getModelRentalModel().getDescription(), controRentalModel.getModelRentalModel().getPrice());
					viewRenalModel.frmRentalModel.setVisible(true);
					
				} else {
					viewRenalModel = new ViewRentalModel(cnpj);
					viewRenalModel.frmRentalModel.setVisible(true);										
					
				}
				
			}
		});
		btnModeloDeAlugual.setBounds(158, 225, 90, 25);
		frmRentalCommunAreas.getContentPane().add(btnModeloDeAlugual);
	}
}
