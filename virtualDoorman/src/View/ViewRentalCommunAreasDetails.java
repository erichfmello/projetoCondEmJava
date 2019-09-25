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
import java.util.Date;
import java.beans.PropertyChangeEvent;

public class ViewRentalCommunAreasDetails {

	public JFrame frmRentalCommunAreasDetails;
	private JComboBox comboBoxAppartment;
	private JDateChooser dateReservation;	
	// Variaveis
	String cnpj;
	int totalAppartaments;
	String[] appartamentsNumber;
	
	// Variaveis do controler
	ControlerAppartment controlerAppartment;
	ControlerAllGuest controlerAllGuest;
	
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
	
	public ViewRentalCommunAreasDetails(String cnpj) {
		this.cnpj = cnpj;
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
				
				DefaultComboBoxModel defaultListModelAppartamentsNumber = new DefaultComboBoxModel();
				defaultListModelAppartamentsNumber.addElement("apto");
				for(int i = 0; i < totalAppartaments; i++) {
					appartamentsNumber[i] = String.valueOf(controlerAppartment.getModelAppartamentThisFloors()[i].getAppartment());
					defaultListModelAppartamentsNumber.addElement(appartamentsNumber[i]);						
					
				}
				comboBoxAppartment.setModel(defaultListModelAppartamentsNumber);
				
			}
		});
		frmRentalCommunAreasDetails.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		frmRentalCommunAreasDetails.setTitle("Detalhes da reserva");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width/2 - 340/2;
		int height = dimension.height/2 - 558/2;
		frmRentalCommunAreasDetails.setBounds(width, height, 339, 557);
		frmRentalCommunAreasDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentalCommunAreasDetails.getContentPane().setLayout(null);
		
		JLabel lblReservationDate = new JLabel("Data da reserva:");
		lblReservationDate.setBounds(10, 11, 100, 14);
		frmRentalCommunAreasDetails.getContentPane().add(lblReservationDate);
		
		dateReservation = new JDateChooser();
		/*				controlerAllGuest = new ControlerAllGuest(dateReservation.getDate());
				controlerAllGuest.selectAllGuestDate();
				
				JOptionPane.showMessageDialog(null, controlerAllGuest.getDate());*/
		
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
		
		JLabel lblListaDeConvidados = new JLabel("Lista de convidados");
		lblListaDeConvidados.setBounds(10, 160, 299, 14);
		frmRentalCommunAreasDetails.getContentPane().add(lblListaDeConvidados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 185, 299, 322);
		frmRentalCommunAreasDetails.getContentPane().add(scrollPane);
		
		JList listGuests = new JList();
		scrollPane.setViewportView(listGuests);
		
		JButton btnAddGuest = new JButton("Adicionar convidado");
		btnAddGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAddGuest = new ViewAddGuest();
				viewAddGuest.frmAddGuest.setVisible(true);
			}
		});
		btnAddGuest.setBounds(120, 105, 189, 23);
		frmRentalCommunAreasDetails.getContentPane().add(btnAddGuest);
		
		JButton btnRemoveGuest = new JButton("Remover convidado");
		btnRemoveGuest.setBounds(120, 139, 189, 23);
		frmRentalCommunAreasDetails.getContentPane().add(btnRemoveGuest);
	}
}
