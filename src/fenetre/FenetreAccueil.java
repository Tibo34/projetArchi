package fenetre;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import BDD.CatalogueDAOFactory;
import BDD.CategorieDAOFactory;
import BDD.DAOConnection;
import BDD.I_CatalogueDAO;
import BDD.I_CategorieDAO;
import BDD.I_ProduitDAO;
import BDD.ProduitDAOFactory;
import Controlleur.ControllerGestionCatalogue;
import Modele.CatalogueFactory;
import Modele.I_Catalogue;
import Modele.I_Categorie;
import Modele.I_Produit;

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
	private List<I_Categorie> listCategorie;

	public FenetreAccueil() {
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
		listeCatalogue=new ArrayList<>();
		catalogueName=new ArrayList<>();
		detailCatalogue=new ArrayList<>();		
		listCategorie=new ArrayList<>();
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
		InitializationCatalogue();
		modifierNbCatalogues();
		setVisible(true);
	}
	
	public void InitializationCatalogue() {
		loadCatalogueOracle();
		loadCatalogueXML();
		
	}
	
	private void loadCatalogueXML() {
		I_CatalogueDAO daoC=CatalogueDAOFactory.getDAOXML();
		I_ProduitDAO daoP=ProduitDAOFactory.getDAOXML();
		loadCatalogue(daoC, daoP);		
	}

	private void loadCatalogueOracle() {
		I_CatalogueDAO daoC=CatalogueDAOFactory.getDAOOracle();
		I_ProduitDAO daoP=ProduitDAOFactory.getDAOOracle();
		loadCatalogue(daoC, daoP);
	}

	private void loadCatalogue(I_CatalogueDAO daoC, I_ProduitDAO daoP) {
		List<String> namesCat=daoC.getNamesCatalogue();
		for(String n : namesCat) {
			I_Catalogue c=CatalogueFactory.createCatalogue(n, daoC, daoP);
			if(!listeCatalogue.contains(c)) {
				addCatalogue(c);
			}
		}
	}

	public void addCatalogue(I_Catalogue n) {
		listeCatalogue.add(n);
		if(!catalogueName.contains(n.getName())) {
			catalogueName.add(n.getName());
			detailCatalogue.add(n.getName()+" : "+n.getNbProduits());
		}
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
				
				deleteCatalogue(texteSupprime);
			}
				System.out.println("supprime catalogue "+texteSupprime);
		}
		if (e.getSource() == btSelectionner)
		{
			String texteSelection = (String)cmbSupprimer.getSelectedItem();
			System.out.println("Catalogue sélectionné : "+texteSelection);
			I_CategorieDAO dao;
			if(texteSelection.contains("xml")) {
				dao=CategorieDAOFactory.getDAOXML();				
			}else {
				dao=CategorieDAOFactory.getDAOOracle();
			}
			List<String>names=dao.getNamesCategorie();
			if (texteSelection != null) 
			{
				I_Catalogue c=getCatalogue(texteSelection);
				ControllerGestionCatalogue.displayCatalogue(c,this,names,dao);
				this.dispose();
			}
		}	
	}

	private void deleteCatalogue(String name) {
		I_Catalogue c=getCatalogue(name);
		I_CatalogueDAO dao=null;
		if(name.contains("xml")) {
			dao=CatalogueDAOFactory.getDAOXML();
		}
		else{
			dao=CatalogueDAOFactory.getDAOOracle();
		}
		dao.deleteCatalogue(name);
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
		I_CatalogueDAO daoCatalogue=null;
		I_ProduitDAO daoProduit=null;
		I_CategorieDAO daoCategorie=null;
		if(name.contains("xml")) {
			daoCatalogue=CatalogueDAOFactory.getDAOXML();
			daoProduit=ProduitDAOFactory.getDAOXML();
			daoCategorie=CategorieDAOFactory.getDAOXML();
		}
		else{
			daoCatalogue=CatalogueDAOFactory.getDAOOracle();
			daoProduit=ProduitDAOFactory.getDAOOracle();	
			daoCategorie=CategorieDAOFactory.getDAOOracle();
		}
		cat=createNewCatalogue(name,daoCatalogue,daoProduit);	
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
