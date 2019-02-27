package Controlleur;

import java.util.List;

import Modele.I_Catalogue;
import fenetre.FenetreAccueil;
import fenetre.FenetrePrincipale;

public class ControllerGestionCatalogue {
	
	public static void displayCatalogue(I_Catalogue c,FenetreAccueil f) {
		FenetrePrincipale principal=new FenetrePrincipale(c,f);
	}
	
	
	public static void closeframeCatalogue(FenetreAccueil f) {
		List<I_Catalogue> list=f.getListeCatalogue();
		FenetreAccueil fenetre =new FenetreAccueil();
		fenetre.addCatalogues(list);
	}
	

}
