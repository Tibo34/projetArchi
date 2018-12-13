package Controlleur;

import Modele.I_Catalogue;
import fenetre.FenetreAffichage;

public class ControllerStock {
	
	public static void afficher(I_Catalogue c) {
		new FenetreAffichage(c);
	}

}
