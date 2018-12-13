package Controlleur;

import Modele.I_Catalogue;
import fenetre.FenetreNouveauProduit;
import fenetre.FenetreSuppressionProduit;

public class ControllerCreerSupprimer {

	
	public static void afficherAjouter() {
		new FenetreNouveauProduit();
	}
	
	public static void afficherSupprimer(I_Catalogue catalogue) {
		new FenetreSuppressionProduit(catalogue);
	}
	
	
}
