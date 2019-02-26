package fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import BDD.I_CatalogueDAO;
import BDD.I_ProduitDAO;
import BDD.ProduitDAO;
import BDD.ProduitDAOFactory;

import java.util.*;
import java.util.List;

import Modele.*;

public class FenetreAccueil extends JFrame implements ActionListener,WindowListener {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;
	private List<I_Catalogue> listeCatalogue;
	private List<String> catalogueName;
	private List<String> detailCatalogue;
	private I_CatalogueDAO daoCatalogue;

	public FenetreAccueil() {
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
		listeCatalogue=new ArrayList<>();
		catalogueName=new ArrayList<>();
		detailCatalogue=new ArrayList<>();
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);
		
		
		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);
		
		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);
		
		panGestionCatalogues.setLayout (new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");
		
		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");
				
		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

		btAjouter.addActionListener(this);
		btSupprimer.addActionListener(this);
		btSelectionner.addActionListener(this);
		
		//String[] tab  = {"Formacia" , "Le Redoutable", "Noitaicossa"}; 
		//modifierListesCatalogues(tab);
		//String[] tab2 = {"Formacia : 6 produits" , "Le Redoutable : 4 produits" , "Noitaicossa : 0 produits" };
		//modifierDetailCatalogues(tab2);
		modifierNbCatalogues();
		setVisible(true);
	}
	
	public void addCatalogue(I_Catalogue n) {
		listeCatalogue.add(n);
		catalogueName.add(n.getName());
		modifierDetailCatalogues(detailCatalogue);
		modifierListesCatalogues(catalogueName);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter)
		{
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals(""))
			{
				System.out.println("ajouter le catalogue "+texteAjout);
				String name=txtAjouter.getText();
				txtAjouter.setText(null);
				I_Catalogue catalogue=CatalogueFactory.createCatalogue(name);
				addCatalogue(catalogue);
				repaint();
				
			}
		}
		if (e.getSource() == btSupprimer)
		{
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null)
				System.out.println("supprime catalogue "+texteSupprime);
		}
		if (e.getSource() == btSelectionner)
		{
			String texteSelection = (String)cmbSupprimer.getSelectedItem();
			if (texteSelection != null) 
			{
				System.out.println("selectionne catalogue "+texteSelection);
				this.dispose();
			}
		}	
	}
	
	private void modifierListesCatalogues(List<String> noms) {
		String[]names=new String[noms.size()];
		noms.toArray(names);
		modifierListesCatalogues(names);
		repaint();
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		if (nomsCatalogues != null)
			for (int i=0 ; i<nomsCatalogues.length; i++)
			{
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
	}
	
	private void modifierNbCatalogues()
	{
		lbNbCatalogues.setText(catalogueName.size() + " catalogues");
	}
	
	private void modifierDetailCatalogues(List<String> detail) {
		String[]names=new String[detail.size()];
		detail.toArray(names);
		modifierDetailCatalogues(names);		
	}
	
	private void modifierDetailCatalogues(String[] detailCatalogues) {
		taDetailCatalogues.setText("");
		if (detailCatalogues != null) {
			for (String detail : detailCatalogues) {
				taDetailCatalogues.append(detail+"\n");
			}
		}
	}
	
	public static void main(String[] args) {
		new FenetreAccueil();
	}

	public void addNamesCatalogue(List<String> c, I_CatalogueDAO d) {
		catalogueName.addAll(c);		
		daoCatalogue=d;
		modifierListesCatalogues(catalogueName);
		for (String str : catalogueName) {
			I_Catalogue catalogue=CatalogueFactory.createCatalogue(str);
			I_ProduitDAO daoProduit=ProduitDAOFactory.getDAOOracle();
			List<I_Produit> produits=daoProduit.getAllProduits(str);
			catalogue.addProduits(produits);
			listeCatalogue.add(catalogue);
			addDetail(str+" "+produits.size());
		}
		modifierNbCatalogues();
		repaint();
	}

	private void addDetail(String str) {
		detailCatalogue.add(str);
		modifierDetailCatalogues(detailCatalogue);
	}

	public I_CatalogueDAO getDaoCatalogue() {
		return daoCatalogue;
	}

	public void setDaoCatalogue(I_CatalogueDAO daoCatalogue) {
		this.daoCatalogue = daoCatalogue;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}