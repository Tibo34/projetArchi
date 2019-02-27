package BDD;

public class ProduitDAOFactory {
	
    private static I_ProduitDAO daoInstance = null;

    private ProduitDAOFactory() {}

    public static I_ProduitDAO getDAOXML() {
        if (daoInstance == null || !(daoInstance instanceof ProduitDAO_XML_Adapter))
            daoInstance = new ProduitDAO_XML_Adapter();
        return daoInstance;
    }
    
    public static I_ProduitDAO getDAOOracle() {
    	if(daoInstance==null|| !(daoInstance instanceof ProduitDAO)) {
    		daoInstance=new ProduitDAO();
    	}
    	return daoInstance;
    }
}
