package BDD;

public class ProduitDAOFactory {
	
	
	
    public static I_ProduitDAO getDAO() {    	
        return new ProduitDAO();
    }
}
