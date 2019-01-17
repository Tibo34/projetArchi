package BDD;

public class ProduitDAOFactory {
	
	private static I_ProduitDAO instance;
	
	private ProduitDAOFactory() {
		
	}
	
	
    public static I_ProduitDAO getDAOInstance() {
    	if(instance==null) {
    		instance=new ProduitDAO();
    	}
        return instance;
    }
}
