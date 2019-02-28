package Modele;

import BDD.I_CatalogueDAO;

public class CategorieFactory {
	
	

	public static I_Categorie createCategorie(int pos, String name, String value) {
		return new Categorie(pos, name, Double.parseDouble(value));
	}
	
	public static I_Categorie createCategorie(String n,String v) {
		double d=Double.parseDouble(v);
		return new Categorie(n,d);
	}

}
