package Controlleur;

import java.util.List;

import javax.swing.JFrame;

import BDD.CategorieDAOFactory;
import BDD.I_CategorieDAO;
import Modele.CategorieFactory;
import Modele.I_Catalogue;
import Modele.I_Categorie;
import fenetre.FenetreNouveauProduit;
import fenetre.FenetreSuppressionProduit;

public class ControllerCreerSupprimer {

	
	public static void afficherAjouter(JFrame fenetrePrincipale, I_Catalogue catalogue,List<String>names) {
		new FenetreNouveauProduit(catalogue,names);
	}
	
	public static void afficherSupprimer(JFrame fenetrePrincipale, I_Catalogue catalogue) {
		new FenetreSuppressionProduit(fenetrePrincipale,catalogue);
	}
	
	public static boolean creerProduit(String nom, double prix, int qte,String categorie,I_Catalogue catalogue) {		
		I_CategorieDAO dao=CategorieDAOFactory.getDAOCategorie();
		I_Categorie c=dao.getCategorie(categorie);
		return catalogue.addProduit(nom, prix, qte,c);
	}
	
	public static boolean supprimerProduit(String nom,I_Catalogue catalogue) {
		 return catalogue.removeProduit(nom);
		
	}
	
}
