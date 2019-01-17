package BDD;

public class ProduitFactory {
    public I_ProduitDAO getDAO() {
        return new ProduitDAO();
    }
}
