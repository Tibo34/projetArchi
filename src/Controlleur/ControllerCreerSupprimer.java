package Controlleur;

import Modele.I_Catalogue;
import fenetre.FenetreNouveauProduit;
import fenetre.FenetreSuppressionProduit;

public class ControllerCreerSupprimer {

	
	public static void afficherAjouter(I_Catalogue catalogue) {
		new FenetreNouveauProduit(catalogue);
	}
	
	public static void afficherSupprimer(I_Catalogue catalogue) {
		new FenetreSuppressionProduit(catalogue);
	}
	
	public static void creerProduit(String nom, double prix, int qte,I_Catalogue catalogue) {
		boolean r=catalogue.addProduit(nom, prix, qte);
	}
	
	public static void supprimerProduit(String nom,I_Catalogue catalogue) {
		boolean r=catalogue.removeProduit(nom);
	}
	
}
