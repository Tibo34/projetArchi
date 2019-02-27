package Modele;

public class CatalogueFactory {
	
	public static I_Catalogue createCatalogue(String name) {
		return new Catalogue(name);
	}

	

}
