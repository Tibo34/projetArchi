package Controlleur;

import Modele.I_Catalogue;
import fenetre.FenetreAffichage;
import fenetre.FenetrePrincipale;

public class ControllerStock {
	
	public static void afficher(FenetrePrincipale fenetrePrincipale, I_Catalogue c) {
		new FenetreAffichage(c);
	}

}
