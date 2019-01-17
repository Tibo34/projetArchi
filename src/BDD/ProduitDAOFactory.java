package BDD;

public class ProduitDAOFactory {
    private static I_ProduitDAO daoInstance = null;

    private ProduitDAOFactory() {}

    public static I_ProduitDAO getDAO() {
        if (daoInstance == null)
            daoInstance = new ProduitDAO();

        return daoInstance;
    }
}
