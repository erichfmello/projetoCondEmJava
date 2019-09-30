package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import Controler.ControlerRentalCommunAreas;
import Controler.ControlerRentalModel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewRentalCommunArea {

	public JFrame frmRentalCommunAreas;
	private JList listRentalModel;
	private JList listRental;
	int totalRentalModel = 0;
	int totalReservationDate = 0;
	private String[] description;
	private double[] price;
	private Date reservationDate = new Date();
	private int reservationAppartament;
	
	// Variaveis comuns
	private String cnpj;
	
	// Variaveis de View
	ViewRentalModel viewRenalModel;
	ViewRentalCommunAreasDetails viewRentalCommunAreasDetails;
	
	// Variaveis do controler
	ControlerRentalModel controRentalModel = new ControlerRentalModel();
	ControlerRentalCommunAreas controlerRentalCommunAreas = new ControlerRentalCommunAreas();

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
		frmRentalCommunAreas.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				seedListRentalModel();
				seedListRentalDate();
				
			}
		});
		frmRentalCommunAreas.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		frmRentalCommunAreas.setTitle("Aluguel de areas");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 139;
		int height = dimension.height/2 - 150;
		frmRentalCommunAreas.setBounds(width, height, 446, 378);
		frmRentalCommunAreas.setResizable(false);
		frmRentalCommunAreas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentalCommunAreas.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneRental = new JScrollPane();
		scrollPaneRental.setBounds(10, 11, 300, 158);
		frmRentalCommunAreas.getContentPane().add(scrollPaneRental);
		
		listRental = new JList();
		scrollPaneRental.setViewportView(listRental);
		
		JButton btnNew = new JButton("Novo");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewRentalCommunAreasDetails = new ViewRentalCommunAreasDetails(cnpj, controRentalModel);
				viewRentalCommunAreasDetails.frmRentalCommunAreasDetails.setVisible(true);
			}
		});
		btnNew.setBounds(326, 11, 100, 25);
		frmRentalCommunAreas.getContentPane().add(btnNew);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listRental.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecionar uma data");
				} else {
					reservationDate = controlerRentalCommunAreas.getModelRentalCommunAreas()[listRental.getSelectedIndex()].getReservationDate();
					
					deleteReservationDate(reservationDate, cnpj);	
					seedListRentalDate();
				}
			}
		});
		btnApagar.setBounds(326, 47, 100, 25);
		frmRentalCommunAreas.getContentPane().add(btnApagar);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmRentalCommunAreas.dispose();
			}
		});
		btnOk.setBounds(326, 83, 100, 25);
		frmRentalCommunAreas.getContentPane().add(btnOk);
		
		JButton btnModeloDeAlugual = new JButton("Modelo");
		btnModeloDeAlugual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listRentalModel.isSelectionEmpty()) {
					viewRenalModel = new ViewRentalModel(cnpj);
					viewRenalModel.frmRentalModel.setVisible(true);					
				} else {
					int selectionRentalModel = listRentalModel.getSelectedIndex();
					viewRenalModel = new ViewRentalModel(cnpj, description[selectionRentalModel], price[selectionRentalModel]);
					viewRenalModel.frmRentalModel.setVisible(true);
				}
				
			}
		});
		btnModeloDeAlugual.setBounds(326, 182, 100, 25);
		frmRentalCommunAreas.getContentPane().add(btnModeloDeAlugual);
		
		JScrollPane scrollPaneRentalModel = new JScrollPane();
		scrollPaneRentalModel.setBounds(10, 180, 300, 158);
		frmRentalCommunAreas.getContentPane().add(scrollPaneRentalModel);
		
		listRentalModel = new JList();
		scrollPaneRentalModel.setViewportView(listRentalModel);
		
		JButton btnDeleteModel = new JButton("Apagar");
		btnDeleteModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectDelete = listRentalModel.getSelectedIndex();
				
				String descriptionDelete = controRentalModel.getModelRentalModel()[selectDelete].getDescription();
				double priceDelete = controRentalModel.getModelRentalModel()[selectDelete].getPrice();
				
				controRentalModel = new ControlerRentalModel(cnpj, descriptionDelete, priceDelete);
				controRentalModel.deleteSelectModel();
				
				seedListRentalModel();
				
			}
		});
		btnDeleteModel.setBounds(326, 216, 100, 25);
		frmRentalCommunAreas.getContentPane().add(btnDeleteModel);
		
		JButton btnGuests = new JButton("Convidados");
		btnGuests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listRental.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione a data do evento");
				}
			}
		});
		btnGuests.setBounds(326, 119, 100, 25);
		frmRentalCommunAreas.getContentPane().add(btnGuests);
	}
	
	// Modularização - Povoação das list
	private void seedListRentalModel() {
		controRentalModel.selectAllRentalModel(cnpj);
		
		totalRentalModel = controRentalModel.getTotalRentalModel();
		
		description = new String[totalRentalModel];
		price = new double[totalRentalModel];
		
		DefaultListModel defaultListModelAllRentalModel = new DefaultListModel();
		for(int i = 0; i < totalRentalModel; i++) {
			description[i] = controRentalModel.getModelRentalModel()[i].getDescription();
			price[i] = controRentalModel.getModelRentalModel()[i].getPrice();
			
			defaultListModelAllRentalModel.add(i, description[i] + " - Valor: R$" + price[i]);
		}
		listRentalModel.setModel(defaultListModelAllRentalModel);
	}
	
	private void seedListRentalDate() {
		controlerRentalCommunAreas.selectAllReservationDates(cnpj);
		
		totalReservationDate = controlerRentalCommunAreas.getTotalReservationDate();
		
		DefaultListModel defalDefaultListModelAllRentalData = new DefaultListModel();
		for(int i = 0; i < totalReservationDate; i++) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			reservationDate = controlerRentalCommunAreas.getModelRentalCommunAreas()[i].getReservationDate();
			reservationAppartament = controlerRentalCommunAreas.getModelRentalCommunAreas()[i].getReservationAppartament();
			
			defalDefaultListModelAllRentalData.add(i, df.format(reservationDate) + " - Apartamento: " + reservationAppartament);
		}
		listRental.setModel(defalDefaultListModelAllRentalData);
	}
	
	// Modularização - Apagar
	private void deleteReservationDate(Date reservationDate, String cnpj) {
		controlerRentalCommunAreas.deleteReservationDate(reservationDate, cnpj);
	}
}
