package fenetre;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;

import javax.swing.*;

import BDD.CatalogueDAO;
import BDD.CatalogueDAOFactory;
import BDD.DAOConnection;
import BDD.I_CatalogueDAO;
import BDD.I_ProduitDAO;
import BDD.ProduitDAO;
import BDD.ProduitDAOFactory;
import Controlleur.ControllerGestionCatalogue;

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
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		modifierNbCatalogues();
		setVisible(true);
	}
	
	public void addCatalogue(I_Catalogue n) {
		listeCatalogue.add(n);
		catalogueName.add(n.getName());
		detailCatalogue.add(n.getName()+" : "+n.getNbProduits());
		modifierDetailCatalogues(detailCatalogue);
		modifierListesCatalogues(catalogueName);
		modifierNbCatalogues();
		repaint();
	}
	
	public void addCatalogues(List<I_Catalogue> list) {
		for (I_Catalogue c : list) {
			addCatalogue(c);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter)
		{
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals(""))
			{
				importCatalogue();				
			}
		}
		if (e.getSource() == btSupprimer)
		{
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null) {
				I_Catalogue c=getCatalogue(texteSupprime);
				deleteCatalogue(c);
			}
				System.out.println("supprime catalogue "+texteSupprime);
		}
		if (e.getSource() == btSelectionner)
		{
			String texteSelection = (String)cmbSupprimer.getSelectedItem();
			System.out.println("Catalogue sélectionné : "+texteSelection);
			if (texteSelection != null) 
			{
				I_Catalogue c=getCatalogue(texteSelection);
				ControllerGestionCatalogue.displayCatalogue(c,this);
				this.dispose();
			}
		}	
	}

	private void deleteCatalogue(I_Catalogue c) {
		listeCatalogue.remove(c);
		catalogueName.remove(c.getName());
		modifierDetailCatalogues(detailCatalogue);
		modifierListesCatalogues(catalogueName);
		modifierNbCatalogues();
		repaint();
	}

	private I_Catalogue getCatalogue(String texteSelection) {
		for (I_Catalogue c : listeCatalogue) {
			if(c.getName().equals(texteSelection)) {
				return c;
			}
		}
		return null;
	}

	private void importCatalogue() {
		String name=txtAjouter.getText();
		txtAjouter.setText(null);
		I_Catalogue cat=null;
		I_CatalogueDAO daoC=null;
		I_ProduitDAO daoP=null;
		if(name.contains("xml")) {
			name=name.substring(0, name.length()-4);
			File filexml=getFileXML();
			daoC=CatalogueDAOFactory.getDAOXML(filexml.getAbsolutePath());
			daoP=ProduitDAOFactory.getDAOXML(filexml.getAbsolutePath());
		}
		else{
			daoC=CatalogueDAOFactory.getDAOOracle();
			daoP=ProduitDAOFactory.getDAOOracle();					
		}
		cat=createNewCatalogue(name,daoC,daoP);	
		addCatalogue(cat);						
		repaint();
	}
	
	private I_Catalogue createNewCatalogue(String name, I_CatalogueDAO daoC, I_ProduitDAO daoP) {
		if(!catalogueExist(name, daoC)) {
			System.out.println("nouveau catalogue");
			daoC.addCatalogue(name);
		}
		I_Catalogue cat=CatalogueFactory.createCatalogue(name,daoC,daoP);		
		return cat;
	}

	private File getFileXML() {
		JFileChooser choose=new JFileChooser("D:\\Java-Projet\\Archi");
		choose.showOpenDialog(null);
		File filexml=choose.getSelectedFile();
		return filexml;
	}

	private boolean catalogueExist(String name,I_CatalogueDAO dao) {
		return dao.exist(name);
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

	public List<I_Catalogue> getListeCatalogue() {
		return listeCatalogue;
	}

	public void setListeCatalogue(List<I_Catalogue> listeCatalogue) {
		this.listeCatalogue = listeCatalogue;
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
		DAOConnection.close();
		System.exit(0);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
