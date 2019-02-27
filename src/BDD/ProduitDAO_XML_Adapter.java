package BDD;

import Modele.I_Catalogue;
import Modele.I_Produit;

import java.util.List;

public class ProduitDAO_XML_Adapter implements I_ProduitDAO {
    private ProduitDAO_XML dao;

    public ProduitDAO_XML_Adapter() {
        dao = new ProduitDAO_XML();
    }

    @Override
    public boolean addNouveauProduit(I_Produit p,I_Catalogue c) {
        return dao.creer(p);
    }

    @Override
    public boolean editProduit(I_Produit p,I_Catalogue c) {
        return dao.maj(p);
    }

    @Override
    public boolean achatProduit(I_Produit p,I_Catalogue c) {
        return dao.maj(p);
    }

    @Override
    public boolean venteProduit(I_Produit p,I_Catalogue c) {
        return dao.maj(p);
    }

    @Override
    public boolean delProduit(I_Produit p,I_Catalogue c) {
        return dao.supprimer(p);
    }

    @Override
    public I_Produit getProduit(String name) {
        return dao.lire(name);
    }
    
	@Override
	public List<I_Produit> getAllProduits(String name) {
		return dao.lireTous();
	}
}
