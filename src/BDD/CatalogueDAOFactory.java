package BDD;

public class CatalogueDAOFactory {
	
	private static I_CatalogueDAO daoInstance = null;

    private CatalogueDAOFactory() {}

    public static I_CatalogueDAO getDAO() {
        if (daoInstance == null)
            daoInstance = new CatalogueDAO();
        return daoInstance;
    }

}
