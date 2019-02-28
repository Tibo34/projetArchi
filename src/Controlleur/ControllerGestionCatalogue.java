package Controlleur;

import java.util.List;

import BDD.I_CategorieDAO;
import Modele.I_Catalogue;
import fenetre.FenetreAccueil;
import fenetre.FenetrePrincipale;

public class ControllerGestionCatalogue {
	
	public static void displayCatalogue(I_Catalogue c,FenetreAccueil f,List<String> categorie,I_CategorieDAO dao) {
		FenetrePrincipale principal=new FenetrePrincipale(c,f,categorie,dao);
	}
	
	
	public static void closeframeCatalogue(FenetreAccueil f) {
		List<I_Catalogue> list=f.getListeCatalogue();
		FenetreAccueil fenetre =new FenetreAccueil();
		fenetre.addCatalogues(list);
	}
	

}
