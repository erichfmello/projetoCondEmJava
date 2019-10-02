package View;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Controler.ControlerAppartment;
import Controler.ControlerOccurence;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewAllOccurence {

	public JFrame frmViewAllOccurrence;
	JList listAllOccurrence;
	
	// Variaveis Comuns
	private String cnpj;
	
	private int totalOccurence;
	private int appartament;
	
	private Date occurenceDate;
	
	private int selectedOccurence;
	
	// Variaveis das View
	ViewOccurenceDetails viewOccurenceDetails;
	
	// Variaveis controler
	ControlerAppartment controlerAppartment;
	ControlerOccurence controlerOccurence;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllOccurence window = new ViewAllOccurence();
					window.frmViewAllOccurrence.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAllOccurence() {
		initialize();
	}
	
	public ViewAllOccurence(String cnpj, ControlerAppartment controlerAppartment) {
		this.cnpj = cnpj;
		this.controlerAppartment = controlerAppartment;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewAllOccurrence = new JFrame();
		frmViewAllOccurrence.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				seedAllOccurrence();
			}
		});
		frmViewAllOccurrence.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\livroNegro.png"));
		frmViewAllOccurrence.setTitle("Livro negro");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 340/2;
		int height = dimension.height/2 - 300/2;
		frmViewAllOccurrence.setBounds(width, height, 340, 300);
		frmViewAllOccurrence.setResizable(false);
		frmViewAllOccurrence.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewAllOccurrence.getContentPane().setLayout(null);
		
		JLabel lblOcorrnci = new JLabel("Ocorr\u00EAncia:");
		lblOcorrnci.setBounds(10, 11, 113, 14);
		frmViewAllOccurrence.getContentPane().add(lblOcorrnci);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 200, 214);
		frmViewAllOccurrence.getContentPane().add(scrollPane);
		
		listAllOccurrence = new JList();
		scrollPane.setViewportView(listAllOccurrence);
		
		JButton btnNew = new JButton("Novo");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewOccurenceDetails = new ViewOccurenceDetails(cnpj, controlerAppartment);
				viewOccurenceDetails.frmOccurenceDetails.setVisible(true);
			}
		});
		btnNew.setBounds(220, 34, 90, 25);
		frmViewAllOccurrence.getContentPane().add(btnNew);
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listAllOccurrence.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione uma ocorr�ncia");
				} else {
					occurenceDate = controlerOccurence.getModelOccurrence()[listAllOccurrence.getSelectedIndex()].getDate();
					appartament = controlerOccurence.getModelOccurrence()[listAllOccurrence.getSelectedIndex()].getAppartament();
					
					controlerOccurence.selectOccurence(cnpj, occurenceDate, appartament);
					viewOccurenceDetails = new ViewOccurenceDetails(cnpj, controlerAppartment, controlerOccurence, appartament);
					viewOccurenceDetails.frmOccurenceDetails.setVisible(true);
				}
			}
		});
		btnEdit.setBounds(220, 70, 90, 25);
		frmViewAllOccurrence.getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listAllOccurrence.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione uma ocorr�ncia");
				} else {
					deleteOccurence();
					seedAllOccurrence();
				}
			}
		});
		btnDelete.setBounds(220, 106, 90, 25);
		frmViewAllOccurrence.getContentPane().add(btnDelete);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmViewAllOccurrence.dispose();
			}
		});
		btnOk.setBounds(220, 225, 90, 25);
		frmViewAllOccurrence.getContentPane().add(btnOk);
	}
	
	// Modulariza��o
	private void seedAllOccurrence() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		controlerOccurence = new ControlerOccurence();
		controlerOccurence.selectAllOccurence(cnpj);
		
		totalOccurence = controlerOccurence.getTotalOccurence();
				
		DefaultListModel defaultListModelAllOccurence = new DefaultListModel();
		for(int i = 0; i < totalOccurence; i++) {
			occurenceDate = controlerOccurence.getModelOccurrence()[i].getDate();
			appartament = controlerOccurence.getModelOccurrence()[i].getAppartament();
			
			defaultListModelAllOccurence.add(i, df.format(occurenceDate) + " - Apartamento: " + appartament);
		}
		listAllOccurrence.setModel(defaultListModelAllOccurence);
		
	}
	
	private void deleteOccurence() {
		selectedOccurence = listAllOccurrence.getSelectedIndex();
		
		occurenceDate = controlerOccurence.getModelOccurrence()[selectedOccurence].getDate();
		
		controlerOccurence = new ControlerOccurence();
		controlerOccurence.deleteOccurence(cnpj, occurenceDate);
		
	}
}