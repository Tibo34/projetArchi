package Modele;

import BDD.I_ProduitDAO;

public class CatalogueFactory {
	
	public static I_Catalogue createCatalogue(String name) {
		return new Catalogue(name);
	}

	

}
