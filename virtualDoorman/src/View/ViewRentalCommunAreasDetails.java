package View;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import Controler.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.SwingConstants;

public class ViewRentalCommunAreasDetails {

	public JFrame frmRentalCommunAreasDetails;
	private JComboBox comboBoxAppartment;
	JComboBox comboBoxRentalModel;
	private JDateChooser dateReservation;	
	
	// Variaveis
	String cnpj;
	String description;
	String[] appartamentsNumber;
	int totalAppartaments;

	String[] rentalModel;
	double[] rentalModelPrice;
	int totalRentalModel;
	
	private Date reservationDate = new Date();
	private int reservationAppartament;
	
	// Variaveis do controler
	ControlerAppartment controlerAppartment;
	ControlerAllGuest controlerAllGuest;
	ControlerRentalCommunAreas controlerRentalCommunAreas;
	ControlerRentalModel controlerRentalModel;
	
	// Variaveis das View
	ViewAddGuest viewAddGuest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRentalCommunAreasDetails window = new ViewRentalCommunAreasDetails();
					window.frmRentalCommunAreasDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewRentalCommunAreasDetails() {
		initialize();
	}
	
	public ViewRentalCommunAreasDetails(String cnpj, ControlerRentalModel controlerRentalModel) {
		this.cnpj = cnpj;
		this.controlerRentalModel = controlerRentalModel;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentalCommunAreasDetails = new JFrame();
		frmRentalCommunAreasDetails.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {				
				controlerAppartment = new ControlerAppartment();
				controlerAppartment.selectAllAppartaments(cnpj);
				
				totalAppartaments = controlerAppartment.getTotalAppartaments();
				appartamentsNumber = new String[totalAppartaments];
				
				controlerRentalModel = new ControlerRentalModel();
				controlerRentalModel.selectAllRentalModel(cnpj);
				//JOptionPane.showMessageDialog(null, controlerRentalModel.getModelRentalModel()[0].getDescription());
				
				totalRentalModel = controlerRentalModel.getTotalRentalModel();
				rentalModel = new String[totalRentalModel];
				rentalModelPrice = new double[totalRentalModel];
				
				DefaultComboBoxModel defaultListModelAppartamentsNumber = new DefaultComboBoxModel();
				defaultListModelAppartamentsNumber.addElement("apto");
				for(int i = 0; i < totalAppartaments; i++) {
					appartamentsNumber[i] = String.valueOf(controlerAppartment.getModelAppartamentThisFloors()[i].getAppartment());
					defaultListModelAppartamentsNumber.addElement(appartamentsNumber[i]);						
					
				}
				comboBoxAppartment.setModel(defaultListModelAppartamentsNumber);
				
				DefaultComboBoxModel defaultComboBoxModelRentalModel = new DefaultComboBoxModel();
				defaultComboBoxModelRentalModel.addElement("Modelo de aluguel");
				for(int i = 0; i < totalRentalModel; i++) {
					rentalModel[i] = String.valueOf(controlerRentalModel.getModelRentalModel()[i].getDescription());
					rentalModelPrice[i] = controlerRentalModel.getModelRentalModel()[i].getPrice();
					defaultComboBoxModelRentalModel.addElement(rentalModel[i] + " - R$" + rentalModelPrice[i]);
				}
				comboBoxRentalModel.setModel(defaultComboBoxModelRentalModel);
				
			}
		});
		frmRentalCommunAreasDetails.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		frmRentalCommunAreasDetails.setTitle("Detalhes da reserva");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 340/2;
		int height = dimension.height/2 - 558/2;
		frmRentalCommunAreasDetails.setBounds(width, height, 339, 170);
		frmRentalCommunAreasDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentalCommunAreasDetails.getContentPane().setLayout(null);
		
		JLabel lblReservationDate = new JLabel("Data da reserva:");
		lblReservationDate.setBounds(10, 11, 100, 14);
		frmRentalCommunAreasDetails.getContentPane().add(lblReservationDate);
		
		dateReservation = new JDateChooser();
		dateReservation.setDateFormatString("dd/MM/yyyy");
		dateReservation.setBounds(110, 11, 100, 20);
		frmRentalCommunAreasDetails.getContentPane().add(dateReservation);
		
		comboBoxAppartment = new JComboBox();
		comboBoxAppartment.setBounds(110, 42, 100, 20);
		frmRentalCommunAreasDetails.getContentPane().add(comboBoxAppartment);
		
		JLabel lblAppartament = new JLabel("Apartamento");
		lblAppartament.setBounds(10, 45, 100, 14);
		frmRentalCommunAreasDetails.getContentPane().add(lblAppartament);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservationDate = dateReservation.getDate();
				reservationAppartament = Integer.parseInt(comboBoxAppartment.getSelectedItem().toString());
				description = rentalModel[comboBoxRentalModel.getSelectedIndex() - 1];
				
				controlerRentalCommunAreas = new ControlerRentalCommunAreas(reservationDate, cnpj, description, reservationAppartament);
				controlerRentalCommunAreas.insertReservation(cnpj);
				
				frmRentalCommunAreasDetails.dispose();
			}
		});
		btnOk.setBounds(220, 7, 89, 23);
		frmRentalCommunAreasDetails.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentalCommunAreasDetails.dispose();
			}
		});
		btnCancel.setBounds(220, 41, 89, 23);
		frmRentalCommunAreasDetails.getContentPane().add(btnCancel);
		
		JLabel lblRentalModel = new JLabel("Modelo de aluguel:");
		lblRentalModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentalModel.setBounds(10, 70, 299, 14);
		frmRentalCommunAreasDetails.getContentPane().add(lblRentalModel);
		
		comboBoxRentalModel = new JComboBox();
		comboBoxRentalModel.setBounds(10, 95, 299, 20);
		frmRentalCommunAreasDetails.getContentPane().add(comboBoxRentalModel);
	}
}
