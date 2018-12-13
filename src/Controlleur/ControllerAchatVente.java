package Controlleur;

import Modele.I_Catalogue;
import fenetre.FenetreAchat;
import fenetre.FenetreVente;

public class ControllerAchatVente {

	
	public static void AfficheAchat(I_Catalogue c) {
		new FenetreAchat(c);
	}
	
	public static void AfficheVente(I_Catalogue c) {
		new FenetreVente(c);
	}
}
