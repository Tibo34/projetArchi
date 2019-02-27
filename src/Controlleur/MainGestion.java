package Controlleur;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import BDD.CatalogueDAOFactory;
import BDD.I_CatalogueDAO;
import BDD.I_ProduitDAO;
import BDD.ProduitDAOFactory;
import Modele.Catalogue;
import Modele.I_Catalogue;
import Utilitaire.Utilitaire;
import fenetre.FenetreAccueil;
import fenetre.FenetrePrincipale;

public class MainGestion {
	
	public static void main(String[]args) {		 
		 //I_Catalogue catalogue = generateCatalogueBD();		 
		 FenetreAccueil accueil=new FenetreAccueil();
		 

	}
	
	public static List<String> addAllCatalogue() {
		I_CatalogueDAO dao=CatalogueDAOFactory.getDAOOracle();
		return dao.getNamesCatalogue();		
	}
	
	/*
	public static I_Catalogue generateCatalogueBD() {
		I_Catalogue catalogue = Catalogue.getInstance();
		I_ProduitDAO dao = ProduitDAOFactory.getDAO();
		catalogue.addProduits(dao.getAllProduits());
		return catalogue;
	}

	public static I_Catalogue generateCatalogueProperties() {
		Properties p = LoadFileProperties();
		I_Catalogue catalogue = Catalogue.getInstance();
		for(Entry<Object, Object> entre : p.entrySet()) {
			double prix=getPrix((String) entre.getValue());
			int qte=getQte((String) entre.getValue());
			catalogue.addProduit((String) entre.getKey(),prix,qte);
		}		
		return catalogue;
	}

	public static Properties LoadFileProperties() {
		 return Utilitaire.loadProperties("properties.propertie");
	}

	private static int getQte(String value) {
		return Integer.parseInt(value.split(" ")[0].split(":")[1]);
	}

	private static double getPrix(String value) {		
		return Double.parseDouble(value.split(" ")[1].split(":")[1]);
	}*/
}
