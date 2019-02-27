package BDD;

public class CatalogueDAOFactory {
	
	private static I_CatalogueDAO daoInstance = null;

    private CatalogueDAOFactory() {}

    public static I_CatalogueDAO getDAOOracle() {
        if (daoInstance == null|| !(daoInstance instanceof CatalogueDAO))
            daoInstance = new CatalogueDAO();
        return daoInstance;
    }
    
    public static I_CatalogueDAO getDAOXML() {
    	if(daoInstance==null||!(daoInstance instanceof CatalogueDAOXML)) {
    		daoInstance=new CatalogueDAOXML();
    	}
    	return daoInstance;
    }

}
