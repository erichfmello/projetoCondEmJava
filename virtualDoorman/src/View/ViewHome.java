package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import Model.*;
import Controler.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.event.ListSelectionEvent;

public class ViewHome {

	private JFrame frmVirtualDoorman;
	JLabel lblResidentialName;
	JList listFloor;
	JList listAppartment;	
	JList listOwner;
	
	protected ViewResidentialData residentialData;
	protected ViewOwner viewOwner;
	protected ViewAllCommunAreas viewAllCommunAreas;
	protected ViewRentalCommunArea viewRentalCommunArea;	
	
	protected ControlerResidentialData controlerResidentialData = new ControlerResidentialData();
	protected ControlerAppartment controlerAppartment = new ControlerAppartment();
	protected ControlerOwner controlerOwner = new ControlerOwner();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHome window = new ViewHome();
					window.frmVirtualDoorman.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVirtualDoorman = new JFrame();
		frmVirtualDoorman.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				seedLists();
			}
		});
		frmVirtualDoorman.setBackground(Color.WHITE);
		frmVirtualDoorman.getContentPane().setBackground(Color.WHITE);
		frmVirtualDoorman.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\principal.jpg"));
		frmVirtualDoorman.setTitle("Porteiro Virtual");
		frmVirtualDoorman.setBounds(100, 100, 632, 488);
		// frmVirtualDoorman.setExtendedState(frmVirtualDoorman.MAXIMIZED_BOTH);
		frmVirtualDoorman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frmVirtualDoorman.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnCadastrarCondominio = new JButton("");
		btnCadastrarCondominio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				residentialData = new ViewResidentialData();
				residentialData.frmResidentialData.setVisible(true);
			}
		});
		btnCadastrarCondominio.setToolTipText("Dados do condom\u00EDnio");
		btnCadastrarCondominio.setIcon(new ImageIcon("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\end3.png"));
		panel.add(btnCadastrarCondominio);
		
		JButton btnOwner = new JButton("");
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexOwner = listOwner.getSelectedIndex();
				
				if(indexOwner == -1) {
					viewOwner = new ViewOwner(controlerResidentialData.getResidentialData().getCnpj());
					viewOwner.frmOwner.setVisible(true);					
				} else {
					indexOwner = controlerOwner.getTotalOwner();
					for(int i = 0; i < indexOwner; i++) {
						if(listOwner.getSelectedValue().toString() == controlerOwner.getModelOwners()[i].getName()) {
							String cnpj = controlerResidentialData.getResidentialData().getCnpj();
							String name = controlerOwner.getModelOwners()[i].getName();
							String rg = controlerOwner.getModelOwners()[i].getRg();
							String cpf = controlerOwner.getModelOwners()[i].getCpf();
							String ddd = controlerOwner.getModelOwners()[i].getDdd();
							String phone = controlerOwner.getModelOwners()[i].getPhone();
							String email = controlerOwner.getModelOwners()[i].getEmail();
							
							viewOwner = new ViewOwner(cnpj, name, rg, cpf, ddd, phone, email);
							viewOwner.frmOwner.setVisible(true);
							break;
						}
					}
				}
			}
		});
		btnOwner.setToolTipText("Adicionar/Verificar morador");
		btnOwner.setIcon(new ImageIcon("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\pessoas.png"));
		panel.add(btnOwner);
		
		JButton btnCommunArea = new JButton("");
		btnCommunArea.setIcon(new ImageIcon("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\areasComunsR2.png"));
		btnCommunArea.setToolTipText("Areas Comuns");
		btnCommunArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAllCommunAreas = new ViewAllCommunAreas(controlerResidentialData.getResidentialData().getCnpj());
				viewAllCommunAreas.frmAllAreas.setVisible(true);
			}
		});
		panel.add(btnCommunArea);
		
		JButton btnRentalCommunArea = new JButton("");
		btnRentalCommunArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewRentalCommunArea = new ViewRentalCommunArea(controlerResidentialData.getResidentialData().getCnpj());
				viewRentalCommunArea.frmRentalCommunAreas.setVisible(true);
			}
		});
		btnRentalCommunArea.setToolTipText("Alugar areas comuns");
		btnRentalCommunArea.setIcon(new ImageIcon("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\aluguelr2.png"));
		panel.add(btnRentalCommunArea);
		
		JPanel panel_1 = new JPanel();
		frmVirtualDoorman.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		lblResidentialName = new JLabel("ResidentialName");
		panel_2.add(lblResidentialName);
		lblResidentialName.setVisible(false);
		lblResidentialName.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblFloor = new JLabel("Andares");
		lblFloor.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblFloor);
		
		JLabel lblAppartment = new JLabel("Apartamentos");
		lblAppartment.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblAppartment);
		
		JLabel lblOwner = new JLabel("Moradores");
		lblOwner.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblOwner);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(1, 3, 5, 0));
		
		JScrollPane scrollPaneFloor = new JScrollPane();
		panel_5.add(scrollPaneFloor);
		
		JScrollPane scrollPaneAppartment = new JScrollPane();
		panel_5.add(scrollPaneAppartment);
		
		JScrollPane scrollPaneOwner = new JScrollPane();
		panel_5.add(scrollPaneOwner);
		
		listFloor = new JList();
		listFloor.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int index;
				if(listFloor.getSelectedValue().toString().length() == 8) {
					index = Integer.parseInt(listFloor.getSelectedValue().toString().substring(0, 1));					
				} else {
					index = Integer.parseInt(listFloor.getSelectedValue().toString().substring(0, 2));
				}
				
				controlerAppartment.selectAppartment(index);
				controlerOwner.selectOwner(index, "andar");
				
				if(controlerResidentialData.getResidentialData().getAppartmentLastFloor() != 0) {
					if(index == controlerResidentialData.getResidentialData().getFloor()+1) {
						DefaultListModel defaultListModelAppartament = new DefaultListModel();
						for(int i = 0; i < controlerResidentialData.getResidentialData().getAppartmentLastFloor(); i++) {
							defaultListModelAppartament.add(i, controlerAppartment.getFloor() + "� Andar - Apartamento: " + controlerAppartment.modelAppartamentThisFloors[i].getAppartment());
						}
						listAppartment.setModel(defaultListModelAppartament);
					} else {
						DefaultListModel defaultListModelAppartament = new DefaultListModel();					
						for(int i = 0; i < controlerResidentialData.getResidentialData().getAppartmentPerFloor(); i++) {
							defaultListModelAppartament.add(i, index + "� Andar - Apartamento: " + controlerAppartment.modelAppartamentThisFloors[i].getAppartment());
							
						}
						listAppartment.setModel(defaultListModelAppartament);
					}
				} else {
					DefaultListModel defaultListModelAppartament = new DefaultListModel();					
					for(int i = 0; i < controlerResidentialData.getResidentialData().getAppartmentPerFloor(); i++) {
						defaultListModelAppartament.add(i, index + "� Andar - Apartamento: " + controlerAppartment.modelAppartamentThisFloors[i].getAppartment());
						
					}
					listAppartment.setModel(defaultListModelAppartament);					
				}
				
				DefaultListModel defaultListModelOwner = new DefaultListModel();
				for(int i = 0; i < controlerOwner.getTotalOwner(); i++) {
					defaultListModelOwner.add(i, controlerOwner.getModelOwners()[i].getName());
				}
				listOwner.setModel(defaultListModelOwner);
				
			}
		});
		scrollPaneFloor.setViewportView(listFloor);
		
		listAppartment = new JList();
		listAppartment.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int indexAppartament = listAppartment.getSelectedValue().toString().length();
				int appartamentSelected;
				
				if(indexAppartament == 26) {
					appartamentSelected = Integer.parseInt(listAppartment.getSelectedValue().toString().substring(24, 26));
					controlerAppartment.selectFloor(appartamentSelected);
				} else {
					appartamentSelected = Integer.parseInt(listAppartment.getSelectedValue().toString().substring(25, 28));
					controlerAppartment.selectFloor(appartamentSelected);
				}
				
				DefaultListModel defaultListModelFloor = new DefaultListModel();
				defaultListModelFloor.add(0, controlerAppartment.getFloor() + "� Andar");
				listFloor.setModel(defaultListModelFloor);
				
				controlerOwner.selectOwner(appartamentSelected, "apartamento");
				DefaultListModel defaultListModelOwner = new DefaultListModel();
				for(int i = 0; i < controlerOwner.getTotalOwner(); i++) {
					defaultListModelOwner.add(i, controlerOwner.getModelOwners()[i].getName());					
				}
				listOwner.setModel(defaultListModelOwner);
				
				
				System.out.println(appartamentSelected);
			}
		});
		scrollPaneAppartment.setViewportView(listAppartment);
		
		listOwner = new JList();
		scrollPaneOwner.setViewportView(listOwner);
		
		JPanel panel_6 = new JPanel();
		frmVirtualDoorman.getContentPane().add(panel_6, BorderLayout.SOUTH);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.setToolTipText("Remover filtros");
		btnRefresh.setIcon(new ImageIcon("C:\\Erich\\Faculdade\\ProjetoCondominio\\img\\refreshR2.png"));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmVirtualDoorman.setVisible(false);
				frmVirtualDoorman.setVisible(true);
				seedLists();
			}
		});
		panel_6.add(btnRefresh);
	}
	
	// Modulariza��o
	public void seedLists() {		
		controlerResidentialData.selectResidentialData();
		lblResidentialName.setText(controlerResidentialData.getResidentialData().getName());
		lblResidentialName.setVisible(true);
		
		// Preenchimento dos andares				
		DefaultListModel defaultListModelFloor = new DefaultListModel();
		int i = 0, k = 0;
		for(i = 0; i < controlerResidentialData.getResidentialData().getFloor(); i++) {
			defaultListModelFloor.add(i, String.valueOf(i+1) + "� Andar");
		}
		if(controlerResidentialData.getResidentialData().getAppartmentLastFloor() > 0) {
			defaultListModelFloor.add(i, String.valueOf(i+1) + "� Andar - Cobertura");
		}
		listFloor.setModel(defaultListModelFloor);
		
		// Preenchimento dos apartamentos
		DefaultListModel defaultListAppartments = new DefaultListModel();
		for(i = 0; i < controlerResidentialData.getResidentialData().getFloor(); i++) {
			for(int j = 0; j < controlerResidentialData.getResidentialData().getAppartmentPerFloor(); j++) {
				defaultListAppartments.add(k, String.valueOf(i+1) + "� Andar - Apartamento: " + String.valueOf(i+1) + String.valueOf(j+1));
				k++;
			}
		}
		for(int j = 0; j < controlerResidentialData.getResidentialData().getAppartmentLastFloor(); j++) {
			defaultListAppartments.add(k, String.valueOf(i+1) + "� Andar - Apartamento: " + String.valueOf(i+1) + String.valueOf(j+1));
			k++;
		}
		listAppartment.setModel(defaultListAppartments);				
		
		// Preenchimento dos moradores
		DefaultListModel defaultListModelOwner = new DefaultListModel();
		controlerOwner.selectOwner();
		for(i = 0; i < controlerOwner.getTotalOwner(); i++) {
			defaultListModelOwner.add(i, controlerOwner.getModelOwners()[i].getName());
		}
		listOwner.setModel(defaultListModelOwner);
		
		System.out.println(controlerResidentialData.getResidentialData().getName());	
	}
}