package Controlleur;

import javax.swing.JFrame;

import Modele.I_Catalogue;
import fenetre.FenetreNouveauProduit;
import fenetre.FenetrePrincipale;
import fenetre.FenetreSuppressionProduit;
import fenetre.PopupMess;

public class ControllerCreerSupprimer {

	
	public static void afficherAjouter(JFrame fenetrePrincipale, I_Catalogue catalogue) {
		new FenetreNouveauProduit(catalogue);
	}
	
	public static void afficherSupprimer(JFrame fenetrePrincipale, I_Catalogue catalogue) {
		new FenetreSuppressionProduit(fenetrePrincipale,catalogue);
	}
	
	public static boolean creerProduit(String nom, double prix, int qte,I_Catalogue catalogue) {		
		return catalogue.addProduit(nom, prix, qte);
	}
	
	public static boolean supprimerProduit(String nom,I_Catalogue catalogue) {
		 return catalogue.removeProduit(nom);
		
	}
	
}
