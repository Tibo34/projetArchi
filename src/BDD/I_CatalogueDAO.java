package BDD;

import java.util.List;

public interface I_CatalogueDAO {


	public abstract List<String> getNamesCatalogue();
	public abstract List<String> getIdCatalogue();
	public abstract int getNombreProduitCatalogue(String name);
	public abstract int getId(String name);
	public abstract boolean exist(String name);
	public abstract void addCatalogue(String string);
	
}
