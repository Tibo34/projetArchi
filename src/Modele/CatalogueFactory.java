package Modele;

import BDD.I_CatalogueDAO;
import BDD.I_ProduitDAO;

public class CatalogueFactory {
	
	public static I_Catalogue createCatalogue(String name) {
		return new Catalogue(name);
	}

	public static I_Catalogue createCatalogue(String name, I_CatalogueDAO daoC,I_ProduitDAO daoP) {
		return new Catalogue(name,daoP,daoC);
	}

	

}
