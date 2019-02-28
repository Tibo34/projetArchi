package Controlleur;

import java.util.List;

import BDD.I_CategorieDAO;
import fenetre.FenetreNouvelleCategorie;
import fenetre.FenetreSuppressionCategorie;

public class ControllerCategorie {
	
	public static void NouvelleCategorie(I_CategorieDAO dao) {
		new FenetreNouvelleCategorie(dao);
	}

	public static void SupprimerCategorie(I_CategorieDAO dao, List<String> listeCategories) {
		new FenetreSuppressionCategorie(listeCategories,dao);
		
	}

}
